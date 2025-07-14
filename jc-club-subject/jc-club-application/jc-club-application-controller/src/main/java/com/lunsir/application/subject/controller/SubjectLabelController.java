package com.lunsir.application.subject.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.lunsir.application.subject.convert.SubjectLabelDTOConverter;
import com.lunsir.application.subject.dto.SubjectLabelDTO;
import com.lunsir.subject.common.entity.Result;
import com.lunsir.subject.domain.entity.SubjectLabelBO;
import com.lunsir.subject.domain.service.SubjectLabelDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lunSir
 * @create 2024-03-03 13:43
 */
@RestController
@RequestMapping("/subject/label")
@Slf4j
public class SubjectLabelController {

    @Resource
    private SubjectLabelDomainService subjectLabelDomainService;


    /**
     * 添加题目标签
     * @param subjectLabelDTO
     * @return
     */
    @PostMapping("/add")
    public Result<Boolean> addSubjectLabel(@RequestBody SubjectLabelDTO subjectLabelDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("添加题目标签:SubjectLabelController.addSubjectLabel.dto: {}", JSON.toJSONString(subjectLabelDTO));
            }
            // 检验参数
            Preconditions.checkArgument(StringUtils.isNotBlank(subjectLabelDTO.getLabelName()),"标签名称不能为空 ！");
            Preconditions.checkNotNull(subjectLabelDTO.getCategoryId(),"分类id不能为空");
            Preconditions.checkNotNull(subjectLabelDTO.getSortNum(),"热度排名参数不能为空");
            // 1. DTO转BO
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.DTOToSubjectLabelBO(subjectLabelDTO);
            // 2. 调用DomainService
            Boolean labelFlag = subjectLabelDomainService.addSubjectLabel(subjectLabelBO);
            // 检验添加时候成功
            Preconditions.checkState(labelFlag,"标签添加失败");
            return Result.ok(true);
        }catch (Exception e){
            log.error("SubjectLabelController.addSubjectLabel.msg: {}",e.getMessage());
            return Result.fail(false,e.getMessage());
        }
    }

    /**
     * 更新标签
     * @param subjectLabelDTO
     * @return
     */
    @PostMapping("/update")
    public Result<Boolean> updateSubjectLabel(@RequestBody SubjectLabelDTO subjectLabelDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("更新标签:SubjectLabelController.updateSubjectLabel.dto: {}", JSON.toJSONString(subjectLabelDTO));
            }

            // 检验参数
            Preconditions.checkNotNull(subjectLabelDTO.getId(),"标签Id不能为空");

            // DTO 转 BO
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.DTOToSubjectLabelBO(subjectLabelDTO);
            // DomainService
            Boolean labelFlag = subjectLabelDomainService.updateSubjectLabel(subjectLabelBO);
            Preconditions.checkState(labelFlag,"更新标签失败");

            return Result.ok(true);
        }catch (Exception e){
            log.error("更新标签:SubjectLabelController.updateSubjectLabel.error.msg: {}",e.getMessage());
            return Result.fail(false,e.getMessage());
        }
    }


    /**
     * 删除标签
     * @param subjectLabelDTO
     * @return
     */
    @PostMapping("/delete")
    public Result<Boolean> deleteSubjectLabel(@RequestBody SubjectLabelDTO subjectLabelDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("更新标签:SubjectLabelController.deleteSubjectLabel.dto: {}", JSON.toJSONString(subjectLabelDTO));
            }

            // 检验参数
            Preconditions.checkNotNull(subjectLabelDTO.getId(),"标签Id不能为空");

            // DTO 转 BO
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.DTOToSubjectLabelBO(subjectLabelDTO);
            // DomainService
            Boolean labelFlag = subjectLabelDomainService.deleteSubjectLabel(subjectLabelBO);
            Preconditions.checkState(labelFlag,"删除标签失败");

            return Result.ok(true);
        }catch (Exception e){
            log.error("更新标签:SubjectLabelController.deleteSubjectLabel.error.msg: {}",e.getMessage());
            return Result.fail(false,e.getMessage());
        }
    }

    /**
     * 根据分类查询标签
     * @param subjectLabelDTO
     * @return
     */
    @PostMapping("/queryLabelByCategoryId")
    public Result<List<SubjectLabelDTO>> getSubjectLabelByCategoryId(@RequestBody SubjectLabelDTO subjectLabelDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("根据分类查询标签:SubjectLabelController.getSubjectLabelByCategoryId.dto: {}", JSON.toJSONString(subjectLabelDTO));
            }

            // 检验参数
            Preconditions.checkNotNull(subjectLabelDTO.getCategoryId(),"题目分类Id不能为空");

            // DTO 转 BO
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.DTOToSubjectLabelBO(subjectLabelDTO);
            // DomainService
            List<SubjectLabelBO> subjectLabelBOList = subjectLabelDomainService.querySubjectLabelByCategoryId(subjectLabelBO);

            // BOList 转 DTOList
            List<SubjectLabelDTO> labelDTOList = SubjectLabelDTOConverter.INSTANCE.DTO_LIST_To_SubjectLabelBO_LIST(subjectLabelBOList);
            return Result.ok(labelDTOList);
        }catch (Exception e){
            log.error("根据分类查询标签:SubjectLabelController.getSubjectLabelByCategoryId.error.msg: {}",e.getMessage());
            return Result.fail(null,e.getMessage());
        }
    }
}
