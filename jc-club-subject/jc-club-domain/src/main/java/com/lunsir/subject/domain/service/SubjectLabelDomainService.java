package com.lunsir.subject.domain.service;

import com.lunsir.subject.domain.entity.SubjectLabelBO;

import java.util.List;

/**
 * @author lunSir
 * @create 2024-03-03 13:55
 */
public interface SubjectLabelDomainService {
    Boolean addSubjectLabel(SubjectLabelBO subjectLabelBO);

    Boolean updateSubjectLabel(SubjectLabelBO subjectLabelBO);

    Boolean deleteSubjectLabel(SubjectLabelBO subjectLabelBO);

    List<SubjectLabelBO> querySubjectLabelByCategoryId(SubjectLabelBO subjectLabelBO);
}
