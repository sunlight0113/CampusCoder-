package com.lunsir.subject.domain.service;

import com.lunsir.subject.common.entity.PageResult;
import com.lunsir.subject.domain.entity.SubjectBO;
import com.lunsir.subject.infra.basic.entity.SubjectInfoEs;

import java.util.List;

/**
 * @author lunSir
 * @create 2024-03-03 16:34
 */
public interface SubjectDomainService {
    Boolean addSubject(SubjectBO subjectBO);

    PageResult<SubjectBO> getSubjectPage(SubjectBO subjectBO);

    SubjectBO querySubjectInfo(SubjectBO subjectBO);

    PageResult<SubjectInfoEs> getSubjectPageBySearch(SubjectBO subjectInfoBO);

    List<SubjectBO> getContributeList();
}
