package com.lunsir.subject.infra.basic.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目标签表(SubjectLabel)表实体类
 *
 * @author makejava
 * @since 2024-03-03 13:39:16
 */
@Data
public class SubjectLabel extends Model<SubjectLabel> {
    //主键
    private Long id;
    //标签分类
    private String labelName;
    //排序
    private Integer sortNum;
    // 分类标签
    private String categoryId;
    //创建人
    private String createdBy;
    //创建时间
    private Date createdTime;
    //更新人
    private String updateBy;
    //更新时间
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

