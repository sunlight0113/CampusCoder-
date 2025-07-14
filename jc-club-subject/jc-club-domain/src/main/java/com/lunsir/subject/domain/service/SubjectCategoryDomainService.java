package com.lunsir.subject.domain.service;

import com.lunsir.subject.domain.entity.SubjectCategoryBO;

import java.util.List;

/**
 * @author lunSir
 * @create 2024-03-01 20:42
 */
public interface SubjectCategoryDomainService {
    void add(SubjectCategoryBO subjectCategoryBO);

    /**
     * 查询分类
     * @param subjectCategoryBO
     */
    List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO);
    Boolean update(SubjectCategoryBO subjectCategoryBO);

    Boolean delete(SubjectCategoryBO subjectCategoryBO);

    Integer queryCountById(Long id);

    List<SubjectCategoryBO> queryCategoryAndLabel(SubjectCategoryBO subjectCategoryBO);
}
