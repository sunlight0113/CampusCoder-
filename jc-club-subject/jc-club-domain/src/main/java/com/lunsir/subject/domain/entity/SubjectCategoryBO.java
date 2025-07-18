package com.lunsir.subject.domain.entity;

import lombok.Data;
import org.springframework.data.relational.core.sql.In;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题目分类(SubjectCategory)实体类
 *
 * @author makejava
 * @since 2024-02-29 21:49:07
 */
@Data
public class SubjectCategoryBO implements Serializable {
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

    List<SubjectLabelBO> subjectLabelBOList;
}

