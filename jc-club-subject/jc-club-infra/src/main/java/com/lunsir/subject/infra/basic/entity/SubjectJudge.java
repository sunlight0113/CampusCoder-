package com.lunsir.subject.infra.basic.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 判断题(SubjectJudge)表实体类
 *
 * @author makejava
 * @since 2024-03-03 15:28:12
 */
@Data
public class SubjectJudge extends Model<SubjectJudge> {
    //主键
    private Long id;
    //题目id
    private Long subjectId;
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

