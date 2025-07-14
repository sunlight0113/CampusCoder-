package com.lunsir.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author lunSir
 * @create 2024-03-04 23:07
 */
@Data
public class SubjectOptionBo implements Serializable {
    // 题目答案
    private String subjectAnswer;

    // 具体题目详情
    private List<SubjectAnswerBO> optionList;
}
