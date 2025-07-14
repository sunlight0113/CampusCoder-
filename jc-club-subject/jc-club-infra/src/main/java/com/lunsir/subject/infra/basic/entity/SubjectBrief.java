package com.lunsir.subject.infra.basic.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 简答题(SubjectBrief)表实体类
 *
 * @author makejava
 * @since 2024-03-03 15:27:10
 */
@Data
public class SubjectBrief extends Model<SubjectBrief> {
    //主键
    private Long id;
    //题目id
    private Long subjectId;
    //题目答案
    private String subjectAnswer;
    //创建人
    private String createdBy;
    //创建时间
    private Date createdTime;
    //更新人
    private String updateBy;
    //更新时间
    private Date updateTime;
    
    private Integer isDeleted;

}

