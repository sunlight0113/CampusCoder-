package com.lunsir.subject.domain.entity;


import com.lunsir.subject.common.entity.PageInfo;
import lombok.Data;

@Data
public class SubjectLikedBO{
    //主键
    private Long id;
    //题目id
    private Long subjectId;
    //点赞人id
    private String likeUserId;
    //点赞状态 1点赞 0不点赞
    private Integer status;
    // 题目名称
    private String subjectName;
    // 当前页数
    private Integer pageNo;
    // 每一页的条数
    private Integer pageSize;

}

