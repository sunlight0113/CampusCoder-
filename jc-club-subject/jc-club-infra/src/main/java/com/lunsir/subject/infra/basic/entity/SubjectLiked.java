package com.lunsir.subject.infra.basic.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;

/**
 * 题目点赞表(SubjectLiked)表实体类
 *
 * @author makejava
 * @since 2024-10-14 14:01:30
 */
@Data
public class SubjectLiked extends Model<SubjectLiked>{
    //主键
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    //题目id
    private Long subjectId;
    //点赞人id
    private String likeUserId;
    //点赞状态 1点赞 0不点赞
    @TableField("`status`")
    private Integer status;
    //创建人
    private String createdBy;
    //创建时间
    private Date createdTime;
    //修改人
    private String updateBy;
    //修改时间
    private Date updateTime;

    private Integer isDeleted;

}

