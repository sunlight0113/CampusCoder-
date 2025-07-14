package com.lunsir.application.subject.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lunSir
 * @create 2024-03-03 16:16
 */
@Data
public class SubjectAnswerDTO implements Serializable{
    /**
     * 答案选项标识
     */
    private Integer optionType;

    /**
     * 答案
     */
    private String optionContent;

    /**
     * 是否正确
     */
    private Integer isCorrect;

}
