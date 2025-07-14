package com.lunsir.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.lunsir.subject.common.entity.PageResult;
import com.lunsir.subject.common.enums.SubjectTypeEnum;
import com.lunsir.subject.common.util.IdWorkerUtil;
import com.lunsir.subject.domain.convert.SubjectDomainConverter;
import com.lunsir.subject.domain.entity.SubjectBO;
import com.lunsir.subject.domain.entity.SubjectOptionBo;
import com.lunsir.subject.domain.factory.SubjectTypeFactory;
import com.lunsir.subject.domain.factory.handler.subject.SubjectTypeAdapter;
import com.lunsir.subject.domain.redis.RedisUtil;
import com.lunsir.subject.domain.service.SubjectDomainService;
import com.lunsir.subject.domain.service.SubjectLikedDomainService;
import com.lunsir.subject.infra.basic.entity.SubjectInfo;
import com.lunsir.subject.infra.basic.entity.SubjectInfoEs;
import com.lunsir.subject.infra.basic.entity.SubjectLabel;
import com.lunsir.subject.infra.basic.entity.SubjectMapping;
import com.lunsir.subject.infra.basic.service.*;
import com.lunsir.subject.common.util.LoginUtil;
import com.lunsir.subject.infra.rpc.UserRPC;
import com.lunsir.subject.infra.rpc.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.weaving.LoadTimeWeaverAware;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.SavepointManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author lunSir
 * @create 2024-03-03 16:34
 */
@Service
@Slf4j
public class SubjectDomainServiceImpl implements SubjectDomainService {

    @Resource
    private SubjectInfoService subjectInfoService;
    @Resource
    private SubjectTypeFactory subjectTypeFactory;

    @Resource
    private SubjectMappingService subjectMappingService;

    @Resource
    private SubjectLabelService subjectLabelService;

    @Resource
    private SubjectEsService subjectEsService;

    @Resource
    private UserRPC userRPC;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private SubjectLikedDomainService subjectLikedDomainService;

    private final String RANK_KEY = "subject_rank";


    /**
     * 这个方法非常重要，要操作三张表
     *
     * @param subjectBO
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public Boolean addSubject(SubjectBO subjectBO) {
        // 1. 添加subject_info表
        SubjectInfo subjectInfo = SubjectDomainConverter.INSTANCE.subjectBoToSubjectInfo(subjectBO);
        subjectInfoService.save(subjectInfo);

        // 2. 根据subjectType字段，判断题目类型，并保存在对应的题目类型的表中
        // 2.1 使用工厂加策略模式
        SubjectTypeAdapter subjectTypeObj = subjectTypeFactory.getObject(SubjectTypeEnum.getByType(subjectBO.getSubjectType()));
        // 判空
        Preconditions.checkNotNull(subjectTypeObj, "subjectType字段非法");

        log.info("SubjectDomainServiceImpl: 当前处理的题目类是：" + subjectTypeObj.toString());
        // 将subject_id传过去
        subjectBO.setId(subjectInfo.getId());
        Boolean add = subjectTypeObj.add(subjectBO);

        // 3. 添加subject_mappings表中(label_id和category_id是对多对的关系)
        List<Long> categoryIds = subjectBO.getCategoryIds();
        List<Long> labelIds = subjectBO.getLabelIds();
        List<SubjectMapping> mappingList = Lists.newArrayList();
        categoryIds.forEach(c -> {
            labelIds.forEach(l -> {
                SubjectMapping subjectMapping = new SubjectMapping();
                subjectMapping.setCategoryId(c);
                subjectMapping.setLabelId(l);
                subjectMapping.setSubjectId(subjectInfo.getId());
                mappingList.add(subjectMapping);
            });
        });
        subjectMappingService.saveBatch(mappingList);
        // 同步到es中
        SubjectInfoEs subjectInfoEs = new SubjectInfoEs();
        subjectInfoEs.setDocId(new IdWorkerUtil(1, 1, 1).nextId());
        subjectInfoEs.setSubjectId(subjectInfo.getId());
        subjectInfoEs.setSubjectAnswer(subjectBO.getSubjectAnswer());
        subjectInfoEs.setCreateTime(new Date().getTime());
        // 这个可以从上下文中获取user的loginId（需要抽取出来）done
        subjectInfoEs.setCreateUser(LoginUtil.getLoginId());
        subjectInfoEs.setSubjectName(subjectInfo.getSubjectName());
        subjectInfoEs.setSubjectType(subjectInfo.getSubjectType());
        subjectEsService.insert(subjectInfoEs);

        // 同步到redis中做贡献排行榜名单功能
        redisUtil.addScore(RANK_KEY,LoginUtil.getLoginId(),1);
        return true;
    }

    /**
     * 查询题目详情： 对subjectInfo做分页处理
     *
     * @param subjectBO
     * @return
     */
    @Override
    public PageResult<SubjectBO> getSubjectPage(SubjectBO subjectBO) {
        PageResult<SubjectBO> pageResult = new PageResult<>();
        pageResult.setPageNo(subjectBO.getPageNo());
        pageResult.setPageSize(subjectBO.getPageSize());
        // 用来分页查询是LIMIT start，end的
        int start = (subjectBO.getPageNo() - 1) * subjectBO.getPageSize();
        SubjectInfo subjectInfo = SubjectDomainConverter.INSTANCE.subjectBoToSubjectInfo(subjectBO);
        // 需要join表，数据量大的时候尽量不要join，后续需要优化！！！
        int count = subjectInfoService.countByCondition(subjectInfo, subjectBO.getCategoryId(), subjectBO.getLabelId());
        if (count == 0) return pageResult;
        List<SubjectInfo> subjectInfoList = subjectInfoService.queryByPage(subjectInfo, subjectBO.getCategoryId(), subjectBO.getLabelId(), start, subjectBO.getPageSize());
        // EntityList转BoList
        List<SubjectBO> subjectBOList = SubjectDomainConverter.INSTANCE.SubjectInfoListToSubjectBOList(subjectInfoList);
        pageResult.setResult(subjectBOList);
        pageResult.setTotal(count);
        return pageResult;
    }

    /**
     * 工厂 + 策略模式
     *
     * @param subjectBO
     * @return
     */
    @Override
    public SubjectBO querySubjectInfo(SubjectBO subjectBO) {
        // 根据ID查subjectInfo
        SubjectInfo subjectInfo = subjectInfoService.getById(subjectBO.getId());
        // 工厂 + 策略分别查询各个类型的题目
        Integer subjectType = subjectInfo.getSubjectType();
        SubjectTypeEnum subjectTypeEnum = SubjectTypeEnum.getByType(subjectType);
        SubjectTypeAdapter subjectTypeAdapter = subjectTypeFactory.getObject(subjectTypeEnum);
        // 工厂核心业务
        SubjectOptionBo optionBo = subjectTypeAdapter.query(subjectInfo.getId());

        // optionBo + subjectInfo 转 subjectBO
        SubjectBO bo = SubjectDomainConverter.
                INSTANCE.optionBoAndSubjectInfoToSubjectBo(optionBo, subjectInfo);
        // 处理labelName
        // 去subjectMapping中查labelIds
        LambdaQueryWrapper<SubjectMapping> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SubjectMapping::getSubjectId, subjectInfo.getId());
        List<SubjectMapping> mappingList = subjectMappingService.list(queryWrapper);
        List<Long> labelIds = mappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        // 去subjectLabel表查labelName
        List<String> labelName = subjectLabelService.listByIds(labelIds).stream().map(SubjectLabel::getLabelName).collect(Collectors.toList());
        bo.setLabelName(labelName);

        bo.setLiked(subjectLikedDomainService.isliked(bo.getId(),LoginUtil.getLoginId()));
        bo.setLikedCount(subjectLikedDomainService.getSubjectLikedCount(bo.getId()));
        List<Long> subjectIdList = subjectInfoService.list().stream().map(SubjectInfo::getId).collect(Collectors.toList());
        int indexOf = subjectIdList.indexOf(subjectBO.getId());
        if(subjectIdList.size() <= 1){
            return bo;
        }
        if (Objects.equals(0,indexOf)){
            bo.setNextSubject(subjectIdList.get(indexOf + 1));
        }else if(Objects.equals(subjectIdList.size()-1,indexOf)){
            bo.setLastSubject(subjectIdList.get(indexOf - 1));
        }else {
            bo.setNextSubject(subjectIdList.get(indexOf + 1));
            bo.setLastSubject(subjectIdList.get(indexOf - 1));
        }
        return bo;
    }

    @Override
    public PageResult<SubjectInfoEs> getSubjectPageBySearch(SubjectBO subjectInfoBO) {
        SubjectInfoEs subjectInfoEs = new SubjectInfoEs();
        subjectInfoEs.setPageNo(subjectInfoBO.getPageNo());
        subjectInfoEs.setPageSize(subjectInfoBO.getPageSize());
        subjectInfoEs.setKeyWord(subjectInfoBO.getKeyWord());
        return subjectEsService.querySubjectList(subjectInfoEs);
    }

    @Override
    public List<SubjectBO> getContributeList() {
        // 由于我们要拿到出题人，出题数量，出题人的id
        // 所以在SubjectBO新增userName，countNum字段
        // 拿到createBy + countNum
        Set<ZSetOperations.TypedTuple<String>> typedTuples = redisUtil.rankWithScore(RANK_KEY, 0, 5);
        if (log.isInfoEnabled()) {
            log.info("SubjectDomainServiceImpl.getContributeList.typedTupleList:{}", JSON.toJSONString(typedTuples));
        }
        List<SubjectBO> subjectBOList = new LinkedList<>();
        // 拿到userName
        for (ZSetOperations.TypedTuple<String> typedTuple : typedTuples) {
            String createBy = typedTuple.getValue();
            Long countNum = typedTuple.getScore().longValue();
            UserInfo userInfo = userRPC.getUserInfo(createBy);
            if (log.isInfoEnabled()) {
                log.info("SubjectDomainServiceImpl.getContributeList.userInfo:{}", JSON.toJSONString(userInfo));
            }
            SubjectBO subjectBO = new SubjectBO();
            subjectBO.setUserName(userInfo.getNickName());
            subjectBO.setAvatar(userInfo.getAvatar());
            subjectBO.setCreatedBy(createBy);
            subjectBO.setCountNum(countNum);
            subjectBOList.add(subjectBO);
        }

        if (log.isInfoEnabled()) {
            log.info("SubjectDomainServiceImpl.getContributeList.subjectBOList:{}", JSON.toJSONString(subjectBOList));
        }

        return subjectBOList;
    }
    /**
     * 用数据库来实现的排行榜
     * @return
     */
//    @Override
//    public List<SubjectBO> getContributeList() {
//        // 由于我们要拿到出题人，出题数量，出题人的id
//        // 所以在SubjectBO新增userName，countNum字段
//        // 拿到createBy + countNum
//        QueryWrapper<SubjectInfo> queryWrapper = new QueryWrapper<>();
//        queryWrapper.select("count(*) as countNum", "created_by as createdBy");
//        queryWrapper.isNotNull("created_by");
//        queryWrapper.groupBy("created_by");
//        queryWrapper.orderByDesc("countNum");
//        queryWrapper.last("limit 0,5");
//        // 由于我们要拿到出题人，出题数量，出题人的id
//        // 所以在SubjectDTO新增userName，countNum字段
//        // 为了不污染infra层，查询的时候直接用List<Map>接
//        List<Map<String, Object>> mapList = subjectInfoService.listMaps(queryWrapper);
//        if (log.isInfoEnabled()) {
//            log.info("SubjectDomainServiceImpl.getContributeList.mapList:{}", JSON.toJSONString(mapList));
//        }
//        List<SubjectBO> subjectBOList = new LinkedList<>();
//        // 拿到userName
//        mapList.forEach(t -> {
//            ;
//            String createBy = (String) t.get("createdBy");
//            UserInfo userInfo = userRPC.getUserInfo(createBy);
//            if (log.isInfoEnabled()) {
//                log.info("SubjectDomainServiceImpl.getContributeList.userInfo:{}", JSON.toJSONString(userInfo));
//            }
//            SubjectBO subjectBO = new SubjectBO();
//            subjectBO.setUserName(userInfo.getNickName());
//            subjectBO.setAvatar(userInfo.getAvatar());
//            subjectBO.setCreatedBy(createBy);
//            subjectBO.setCountNum((Long) t.get("countNum"));
//            subjectBOList.add(subjectBO);
//        });
//
//        if (log.isInfoEnabled()) {
//            log.info("SubjectDomainServiceImpl.getContributeList.subjectBOList:{}", JSON.toJSONString(subjectBOList));
//        }
//
//        return subjectBOList;
//    }


}
