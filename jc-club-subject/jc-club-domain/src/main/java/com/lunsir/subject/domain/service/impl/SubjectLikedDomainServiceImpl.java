package com.lunsir.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lunsir.subject.common.enums.SubjectLikedStateEnum;
import com.lunsir.subject.common.util.LoginUtil;
import com.lunsir.subject.common.util.SubjectLikedBuildRedisKeyUtil;
import com.lunsir.subject.domain.entity.SubjectLikedBO;
import com.lunsir.subject.domain.redis.RedisUtil;
import com.lunsir.subject.domain.service.SubjectLikedDomainService;
import com.lunsir.subject.infra.basic.entity.SubjectInfo;
import com.lunsir.subject.infra.basic.entity.SubjectLiked;
import com.lunsir.subject.infra.basic.service.SubjectInfoService;
import com.lunsir.subject.infra.basic.service.SubjectLikedService;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class SubjectLikedDomainServiceImpl implements SubjectLikedDomainService {


    @Resource
    private RedisUtil redisUtil;
    @Resource
    private SubjectLikedService subjectLikedService;

    @Resource
    private SubjectInfoService subjectInfoService;

    @Override
    public Boolean likeOrUnlike(SubjectLikedBO subjectLikedBO) {
        // 检验states的合法性质并拿到枚举类
        SubjectLikedStateEnum stateEnum = SubjectLikedStateEnum.getSubjectLikedStateEnumByCode(subjectLikedBO.getStatus());

        // 在redis中存hash，用来刷数据库
        String hashKey = SubjectLikedBuildRedisKeyUtil.buildSubjectLikedHashKey(subjectLikedBO.getSubjectId().toString());
        redisUtil.putHash(SubjectLikedBuildRedisKeyUtil.buildPrimaryHashKey(), hashKey, stateEnum.getCode());

        if (Objects.equals(stateEnum, SubjectLikedStateEnum.LIKE)) {
            // 点赞逻辑
            // 1. 该题目点赞数量 + 1
            String userLikedKey = SubjectLikedBuildRedisKeyUtil.buildUserLikedKey(LoginUtil.getLoginId(), subjectLikedBO.getSubjectId().toString());
            // 判断这个user点没点过赞
            if (isliked(subjectLikedBO.getSubjectId(), LoginUtil.getLoginId())) {
                return true;
            }
            String countKey = SubjectLikedBuildRedisKeyUtil.buildSubjectLikedCountKey(subjectLikedBO.getSubjectId().toString());
            redisUtil.increment(countKey, 1L);
            // 2. 在redis设置user的点赞状态
            redisUtil.set(userLikedKey, SubjectLikedStateEnum.LIKE.getCode().toString());
        } else {
            // 取消点赞逻辑
            // 1. 该题目点赞数量 - 1
            String countKey = SubjectLikedBuildRedisKeyUtil.buildSubjectLikedCountKey(subjectLikedBO.getSubjectId().toString());
            Integer countNum = redisUtil.getInt(countKey);
            if (Objects.isNull(countNum) || countNum <= 0) {
                return true;
            }
            redisUtil.decrement(countKey, 1L);
            // 2. 删除redis中对该user设置的点赞状态
            String userLikedKey = SubjectLikedBuildRedisKeyUtil.buildUserLikedKey(LoginUtil.getLoginId(), subjectLikedBO.getSubjectId().toString());
            redisUtil.del(userLikedKey);
        }
        return true;

    }

    /**
     * 判断用户对题目点过赞没有
     *
     * @param subjectId
     * @param userId
     * @return
     */
    @Override
    public Boolean isliked(Long subjectId, String userId) {
        String userLikedKey = SubjectLikedBuildRedisKeyUtil.buildUserLikedKey(userId, subjectId.toString());
        return redisUtil.exist(userLikedKey);
    }

    /**
     * 获取题目点赞数量
     *
     * @param subjectId
     * @return
     */
    @Override
    public Integer getSubjectLikedCount(Long subjectId) {
        Integer count = redisUtil.getInt(SubjectLikedBuildRedisKeyUtil.buildSubjectLikedCountKey(subjectId.toString()));
        if (Objects.isNull(count)) {
            count = 0;
        }
        return count;
    }

    @Override
    public void syncBatchInsertLiked(List<SubjectLiked> subjectLikedList) {
        if (log.isInfoEnabled()) {
            log.info("SubjectLikedDomainServiceImpl.syncBatchInsertLiked.subjectLikedList:{}", JSON.toJSONString(subjectLikedList));
        }
        subjectLikedService.saveBatch(subjectLikedList);
    }

    @Override
    public Page<SubjectLikedBO> getSubjectLikedPage(SubjectLikedBO subjectLikedBO) {
        Integer pageNo = subjectLikedBO.getPageNo();
        Integer pageSize = subjectLikedBO.getPageSize();
        Page<SubjectLiked> pageInfo = new Page<>(pageNo,pageSize);
        subjectLikedService.page(pageInfo);
        if (log.isInfoEnabled()){
            log.info("getSubjectLikedPage.pageInfo:{}",JSON.toJSONString(pageInfo));
        }
        List<SubjectLiked> records = pageInfo.getRecords();
        List<SubjectLikedBO> subjectLikedBOList = new ArrayList<>();
        for (SubjectLiked liked : records) {
            SubjectLikedBO bo = new SubjectLikedBO();
            bo.setSubjectId(liked.getSubjectId());
            bo.setLikeUserId(liked.getLikeUserId());
            bo.setStatus(liked.getStatus());
            bo.setId(liked.getId());
            // 查subjectName
            SubjectInfo subjectInfo = subjectInfoService.getById(liked.getSubjectId());
            bo.setSubjectName(subjectInfo.getSubjectName());
            subjectLikedBOList.add(bo);
        }
        Page<SubjectLikedBO> boPage = new Page<>();
        boPage.setCurrent(pageInfo.getCurrent());
        boPage.setTotal(pageInfo.getTotal());
        boPage.setSize(pageInfo.getSize());
        boPage.setRecords(subjectLikedBOList);
        return boPage;
    }


}

