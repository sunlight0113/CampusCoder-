package com.lunsir.subject.infra.basic.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 多选题信息表(SubjectMultiple)表实体类
 *
 * @author makejava
 * @since 2024-03-03 15:28:58
 */
@Data
public class SubjectMultiple extends Model<SubjectMultiple> {
    //主键
    private Long id;
    //题目id
    private Long subjectId;
    //选项类型
    private Integer optionType;
    //选项内容
    private String optionContent;
    //是否正确
    private Integer isCorrect;
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

