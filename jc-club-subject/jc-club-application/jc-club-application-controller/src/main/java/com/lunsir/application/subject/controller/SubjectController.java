package com.lunsir.application.subject.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.lunsir.application.subject.convert.SubjectDTOConverter;
import com.lunsir.application.subject.dto.SubjectDTO;
import com.lunsir.subject.common.entity.PageResult;
import com.lunsir.subject.common.entity.Result;
import com.lunsir.subject.domain.entity.SubjectBO;
import com.lunsir.subject.domain.service.SubjectDomainService;
import com.lunsir.subject.infra.basic.entity.SubjectInfoEs;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 亮点来了，操作三张表，工厂+策略模式 理解领域层Domain是什么聚合的
 * @author lunSir
 * @create 2024-03-03 15:39
 */

@RestController
@RequestMapping("/subject")
@Slf4j
public class SubjectController {

    @Resource
    SubjectDomainService subjectDomainService;


    /**
     * 添加题目（工厂 + 策略模式）
     * @param subjectDTO
     * @return
     */
    @PostMapping("/add")
    public Result<Boolean> addSubject(@RequestBody SubjectDTO subjectDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("SubjectController.addSubject.SubjectDTO:{}",JSON.toJSONString(subjectDTO));
            }
            // 校验参数
            // subject_info表
            Preconditions.checkArgument(StringUtils.isNotBlank(subjectDTO.getSubjectName()),"题目名称不能为空");
            Preconditions.checkNotNull(subjectDTO.getSubjectDifficult(),"题目难度不能为空");
            Preconditions.checkNotNull(subjectDTO.getSubjectType(),"题目类型不能为空");
            Preconditions.checkState((subjectDTO.getSubjectType() >= 1 && subjectDTO.getSubjectType() <= 4),"题目类型不合法");
            Preconditions.checkNotNull(subjectDTO.getSubjectScore(),"题目分数不能为空");
            Preconditions.checkArgument(StringUtils.isNotBlank(subjectDTO.getSubjectParse()),"题目解析不能为空");

            // subject_mapping表
            Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectDTO.getCategoryIds()),"题目分类Id不能为空");
            Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectDTO.getLabelIds()),"题目标签Id不能为空");

            // SubjectDTO 转换为 SubjectBO
            SubjectBO subjectBO = SubjectDTOConverter.INSTANCE.SubjectDTO_To_SubjectBO(subjectDTO);
            // Controller直接调用DomainService的添加方法
            Boolean addFlag = subjectDomainService.addSubject(subjectBO);
            if (!addFlag) return Result.fail(false);
            return Result.ok(addFlag);
        }catch (Exception e){
            log.error("SubjectController.addSubject.errorMsg:{}",e.getMessage());
            return Result.fail(false,e.getMessage());
        }
    }

    /**
     * 查询题目列表 对SubjectInfo做分页处理
     * @param subjectDTO
     * @return
     */
    @PostMapping("/getSubjectPage")
    public Result<PageResult<SubjectBO>> getSubjectPage(@RequestBody SubjectDTO subjectDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("SubjectController.getSubjectPage.SubjectDTO:{}",JSON.toJSONString(subjectDTO));
            }
            // 校验参数
            Preconditions.checkNotNull(subjectDTO.getCategoryId(),"题目分类id不能为空");
            Preconditions.checkNotNull(subjectDTO.getLabelId(),"题目标签id不能为空");


            // SubjectDTO 转换为 SubjectBO
            SubjectBO subjectBO = SubjectDTOConverter.INSTANCE.SubjectDTO_To_SubjectBO(subjectDTO);
            // Controller直接调用DomainService的添加方法
            PageResult<SubjectBO> pageResult = subjectDomainService.getSubjectPage(subjectBO);
            // BOList转DTOList
            // TODO：需要优化，响应的是一个BO
            return Result.ok(pageResult);
        }catch (Exception e){
            log.error("SubjectController.addSubject.errorMsg:{}",e.getMessage());
            return Result.fail(null,e.getMessage());
        }
    }

    /**
     * 查询题目详情
     * @param subjectDTO
     * @return
     */
    @PostMapping("/querySubjectInfo")
    public Result<SubjectDTO> querySubjectInfo(@RequestBody SubjectDTO subjectDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("SubjectController.querySubjectInfo.SubjectDTO:{}",JSON.toJSONString(subjectDTO));
            }
            // 校验参数
            Preconditions.checkNotNull(subjectDTO.getId(),"题目id不能为空");
            // SubjectDTO 转换为 SubjectBO
            SubjectBO subjectBO = SubjectDTOConverter.INSTANCE.SubjectDTO_To_SubjectBO(subjectDTO);
            // Controller直接调用DomainService的添加方法
            SubjectBO boResult = subjectDomainService.querySubjectInfo(subjectBO);
            // BO转DTO
            SubjectDTO dto = SubjectDTOConverter.INSTANCE.BoToDTO(boResult);
            return Result.ok(dto);
        }catch (Exception e){
            log.error("SubjectController.querySubjectInfo.errorMsg:{}",e.getMessage());
            return Result.fail(null,e.getMessage());
        }
    }

    /**
     * es全文检索
     * @param subjectInfoDTO
     * @return
     */
    @PostMapping("/getSubjectPageBySearch")
    public Result<PageResult<SubjectInfoEs>> getSubjectPageBySearch(@RequestBody SubjectDTO subjectInfoDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectController.getSubjectPageBySearch.dto:{}", JSON.toJSONString(subjectInfoDTO));
            }
            Preconditions.checkArgument(StringUtils.isNotBlank(subjectInfoDTO.getKeyWord()), "关键词不能为空");
            SubjectBO subjectInfoBO = SubjectDTOConverter.INSTANCE.SubjectDTO_To_SubjectBO(subjectInfoDTO);
            subjectInfoBO.setPageNo(subjectInfoDTO.getPageNo());
            subjectInfoBO.setPageSize(subjectInfoDTO.getPageSize());
            PageResult<SubjectInfoEs> boPageResult = subjectDomainService.getSubjectPageBySearch(subjectInfoBO);
            return Result.ok(boPageResult);
        } catch (Exception e) {
            log.error("SubjectCategoryController.getSubjectPageBySearch.error:{}", e.getMessage(), e);
            return Result.fail(null,"全文检索失败");
        }
    }


    /**
     * 题目贡献榜单
     */
    @PostMapping("/getContributeList")
    public Result<List<SubjectDTO>> getContributeList(){
        try {

            List<SubjectBO> subjectBOList =
                    subjectDomainService.getContributeList();
            List<SubjectDTO> subjectDTOList = SubjectDTOConverter.INSTANCE.BOListToDTOList(subjectBOList);
            return Result.ok(subjectDTOList);
        }catch (Exception e){
            log.error("SubjectController.querySubjectInfo.errorMsg:{}",e.getMessage());
            return Result.fail(null,e.getMessage());
        }
    }


}
