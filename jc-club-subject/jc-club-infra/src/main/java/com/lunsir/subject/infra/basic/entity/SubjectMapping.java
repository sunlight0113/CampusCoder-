package com.lunsir.subject.infra.basic.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目分类关系表(SubjectMapping)表实体类
 *
 * @author makejava
 * @since 2024-03-03 15:28:39
 */
@Data
public class SubjectMapping extends Model<SubjectMapping> {
    //主键
    private Long id;
    //题目id
    private Long subjectId;
    //分类id
    private Long categoryId;
    //标签id
    private Long labelId;
    //创建人
    private String createdBy;
    //创建时间
    private Date createdTime;
    //修改人
    private String updateBy;
    //修改时间
    private Date updateTime;
    
    private Integer isDeleted;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
    }

