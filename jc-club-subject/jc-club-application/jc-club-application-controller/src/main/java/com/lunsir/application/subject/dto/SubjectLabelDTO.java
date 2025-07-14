package com.lunsir.application.subject.dto;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * 题目标签表(SubjectLabel)表实体类
 *
 * @author lunsir
 * @since 2024-03-03 13:39:16
 */
@Data
public class SubjectLabelDTO implements Serializable {
    //主键
    private Long id;
    //标签分类
    private String labelName;
    //排序
    private Integer sortNum;
    // 题目分类ID
    private String categoryId;
}

