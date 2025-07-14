package com.lunsir.application.subject.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 题目分类(SubjectCategory)实体类
 *
 * @author makejava
 * @since 2024-02-29 21:49:07
 */
@Data
public class SubjectCategoryDTO implements Serializable {
    private static final long serialVersionUID = 116610131525790104L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 分类类型
     */
    private Integer categoryType;
    /**
     * 图标连接
     */
    private String imageUrl;
    /**
     * 父级id
     */
    private Long parentId;

    /**
     * 分类下的题目数
     */
    private Integer count;

    // 二期优化添加的字段
    List<SubjectLabelDTO> subjectLabelDTOList;
}

