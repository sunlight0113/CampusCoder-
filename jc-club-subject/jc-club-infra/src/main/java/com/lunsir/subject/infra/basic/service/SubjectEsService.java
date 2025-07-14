package com.lunsir.subject.infra.basic.service;

import com.lunsir.subject.common.entity.PageResult;
import com.lunsir.subject.infra.basic.entity.SubjectInfoEs;

/**
 * @author lunSir
 * @create 2024-10-12 18:06
 */
public interface SubjectEsService {

    boolean insert(SubjectInfoEs subjectInfoEs);

    PageResult<SubjectInfoEs> querySubjectList(SubjectInfoEs subjectInfoEs);

}
