package com.lunsir.application.subject.dto;

import com.lunsir.subject.common.entity.PageInfo;
import lombok.Data;
import org.springframework.data.relational.core.sql.In;

import java.io.Serializable;
import java.util.List;

/**
 * @author lunSir
 * @create 2024-03-03 15:43
 */
@Data
public class SubjectDTO extends PageInfo implements Serializable{
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
    // 题目分类id列表
    private List<Long> categoryIds;
    // 题目标签id列表
    private List<Long> labelIds;

    // 题目答案
    private String subjectAnswer;

    // 具体题目详情
    private List<SubjectAnswerDTO> optionList;


    private List<String> labelName;

    // 当前题目页数
    private Integer pageNo;
    // 每页的大小
    private Integer pageSize;
    // 题目标签id
    private Long labelId;
    // 题目分类Id
    private Long categoryId;

    private String keyWord;

    private Long countNum;

    private String userName;

    //创建人
    private String createdBy;

    // 用户头像
    private String avatar;

    // 点赞数
    private Integer likedCount;

    // 该用户是否点赞
    private Boolean liked;

    // 下一题
    private Long nextSubject;
    // 上一题
    private Long lastSubject;
}
