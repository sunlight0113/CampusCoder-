package com.lunsir.application.subject.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.lunsir.application.subject.convert.SubjectCategoryDtoConverter;
import com.lunsir.application.subject.convert.SubjectLabelDTOConverter;
import com.lunsir.application.subject.dto.SubjectCategoryDTO;
import com.lunsir.application.subject.dto.SubjectLabelDTO;
import com.lunsir.subject.common.entity.Result;
import com.lunsir.subject.domain.entity.SubjectCategoryBO;
import com.lunsir.subject.domain.service.SubjectCategoryDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 题目分类Controller
 *
 * @author lunSir
 * @create 2024-03-01 20:58
 */
@RestController
@RequestMapping("subject/category")
@Slf4j
public class SubjectCategoryController {

    @Resource
    private SubjectCategoryDomainService subjectCategoryDomainService;

    /**
     * 添加题目分类！
     *
     * @return
     */
    @PostMapping("/add")
    public Result<Boolean> addSubjectCategory(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("添加分类：Controller: SubjectCategoryDTO: {} ", JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getCategoryType(), "分类ID不能为空 ！");
            Preconditions.checkArgument(StringUtils.isNotBlank(subjectCategoryDTO.getCategoryName()), "分类名称不能为空 ！");
            Preconditions.checkNotNull(subjectCategoryDTO.getImageUrl(), "分类image不能为空 ！");

            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDtoConverter.INSTANCE.
                    SubjectCategoryDtoToSubjectCategoryBO(subjectCategoryDTO);
            // add
            subjectCategoryDomainService.add(subjectCategoryBO);
            return Result.ok(true);
        } catch (Exception e) {
            log.error("添加分类：Controller: {}", e.getMessage());
            return Result.fail(false, e.getMessage());
        }
    }

    /**
     * 查询大分类
     *
     * @param subjectCategoryDTO
     * @return
     */
    @PostMapping("/queryPrimaryCategory")
    public Result<List<SubjectCategoryDTO>> queryPrimaryCategory(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {

        try {
            // 日志
            if (log.isInfoEnabled()) {
                log.info("查询大分类: SubjectCategoryController.queryPrimaryCategory-->CategoryType: {}", subjectCategoryDTO.getCategoryType());
            }
            // 验参
            Preconditions.checkNotNull(subjectCategoryDTO.getCategoryType(), "分类类型不能为空！");
            // converter
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDtoConverter.INSTANCE.SubjectCategoryDtoToSubjectCategoryBO(subjectCategoryDTO);
            List<SubjectCategoryBO> categoryBOList = subjectCategoryDomainService.queryCategory(subjectCategoryBO);
            // BOList 转 DTOList
            List<SubjectCategoryDTO> subjectCategoryDTOList = SubjectCategoryDtoConverter.INSTANCE.convertSubjectCategoryDTOList(categoryBOList);
            return Result.ok(subjectCategoryDTOList);
        } catch (Exception e) {
            log.error("查询大分类: SubjectCategoryController.queryPrimaryCategory:{}", e.getMessage());
            return Result.fail(null, e.getMessage());
        }
    }

    /**
     * 查询大类下分类
     *
     * @param subjectCategoryDTO
     * @return
     */
    @PostMapping("/queryCategoryByPrimary")
    public Result<List<SubjectCategoryDTO>> queryCategoryByPrimary(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            // 日志
            if (log.isInfoEnabled()) {
                log.info("查询大类下分类: SubjectCategoryController.queryCategoryByPrimary-->subjectCategoryDTO: {}", JSON.toJSONString(subjectCategoryDTO));
            }
            // 验参
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(), "父级ID不能为空！");
            Preconditions.checkNotNull(subjectCategoryDTO.getCategoryType(), "分类类型不能为空！");
            // DO 转 BO
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDtoConverter.INSTANCE.SubjectCategoryDtoToSubjectCategoryBO(subjectCategoryDTO);
            // 查询
            List<SubjectCategoryBO> categoryBOList = subjectCategoryDomainService.queryCategory(subjectCategoryBO);
            // BOList转DTOList
            List<SubjectCategoryDTO> subjectCategoryDTOList = SubjectCategoryDtoConverter.INSTANCE.convertSubjectCategoryDTOList(categoryBOList);
            return Result.ok(subjectCategoryDTOList);
        } catch (Exception e) {
            log.error("查询大类下分类: SubjectCategoryController.queryCategoryByPrimary: {}", e.getMessage());
            return Result.fail(null, e.getMessage());
        }
    }

    /**
     * 更新分类
     *
     * @param subjectCategoryDTO
     * @return
     */
    @PostMapping("/update")
    public Result<Boolean> updateSubjectCategory(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("更新分类: SubjectCategoryController.updateSubjectCategory: {}", JSON.toJSONString(subjectCategoryDTO));
            }
            // 校验
            Preconditions.checkNotNull(subjectCategoryDTO.getId(), "分类id不能为空！");
            // DTO 转 BO
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDtoConverter.INSTANCE.SubjectCategoryDtoToSubjectCategoryBO(subjectCategoryDTO);
            Boolean isUpdate = subjectCategoryDomainService.update(subjectCategoryBO);
            if (!isUpdate) return Result.fail(false, "更新失败");
            return Result.ok(isUpdate);
        } catch (Exception e) {
            log.error("更新分类: SubjectCategoryController.updateSubjectCategory: {}", e.getMessage());
            return Result.fail(false, e.getMessage());
        }
    }

    /**
     * 删除分类
     *
     * @param subjectCategoryDTO
     * @return
     */
    @PostMapping("/delete")
    public Result<Boolean> deleteSubjectCategory(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("删除分类: SubjectCategoryController.deleteSubjectCategory: {}", JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getId(), "分类ID不能为空！");
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDtoConverter.INSTANCE.SubjectCategoryDtoToSubjectCategoryBO(subjectCategoryDTO);
            Boolean is_delete = subjectCategoryDomainService.delete(subjectCategoryBO);
            if (!is_delete) return Result.fail(false, "删除失败");
            return Result.ok(true);
        } catch (Exception e) {
            log.error("删除分类: SubjectCategoryController.deleteSubjectCategory: {}", e.getMessage());
            return Result.fail(false, e.getMessage());
        }
    }

    /**
     * 查询当前大分类下面的所有分类以及标签（二期优化）
     *
     * @param subjectCategoryDTO
     * @return
     */
    @PostMapping("/queryCategoryAndLabel")
    public Result<List<SubjectCategoryDTO>> queryCategoryAndLabel(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            // 打印日志
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.queryCategoryAndLabel:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            // 校验参数
            Preconditions.checkNotNull(subjectCategoryDTO.getId(), "分类ID不能为空！！！");
            // Dto转BO
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDtoConverter.INSTANCE.SubjectCategoryDtoToSubjectCategoryBO(subjectCategoryDTO);
            // 调用DomainService层
            List<SubjectCategoryBO> subjectCategoryBOList = subjectCategoryDomainService.queryCategoryAndLabel(subjectCategoryBO);
            // BOList 转 DTOList
            List<SubjectCategoryDTO> subjectCategoryDTOList = new ArrayList<>();
            subjectCategoryBOList.forEach(bo -> {
                SubjectCategoryDTO categoryDTO = SubjectCategoryDtoConverter.INSTANCE.boToDTO(bo);
                List<SubjectLabelDTO> labelDTOList = SubjectLabelDTOConverter.INSTANCE.DTO_LIST_To_SubjectLabelBO_LIST(bo.getSubjectLabelBOList());
                categoryDTO.setSubjectLabelDTOList(labelDTOList);
                subjectCategoryDTOList.add(categoryDTO);
            });
            return Result.ok(subjectCategoryDTOList);
        } catch (Exception e) {
            log.error("查询当前大分类下面的所有分类以及标签（二期优化）失败：SubjectCategoryController.queryCategoryAndLabel.{}", e.getMessage());
            return Result.fail(null, e.getMessage());
        }
    }
}
