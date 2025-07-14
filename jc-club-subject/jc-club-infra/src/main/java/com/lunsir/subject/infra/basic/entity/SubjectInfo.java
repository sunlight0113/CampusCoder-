package com.lunsir.subject.infra.basic.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目信息表(SubjectInfo)表实体类
 *
 * @author makejava
 * @since 2024-03-03 15:27:46
 */
@Data
public class SubjectInfo extends Model<SubjectInfo> {
    //主键
    private Long id;
    //题目名称
    private String subjectName;
    //题目难度
    private Integer subjectDifficult;
    //出题人名
    private String settleName;
    //题目类型 1单选 2多选 3判断 4简答
    private Integer subjectType;
    //题目分数
    private Integer subjectScore;
    //题目解析
    private String subjectParse;
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

