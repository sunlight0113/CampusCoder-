package com.lunsir.subject.domain.factory.handler.subject;

import com.lunsir.subject.common.enums.SubjectTypeEnum;
import com.lunsir.subject.domain.entity.SubjectAnswerBO;
import com.lunsir.subject.domain.entity.SubjectBO;
import com.lunsir.subject.domain.entity.SubjectOptionBo;

/**
 * @author lunSir
 * @create 2024-03-03 17:28
 */
public interface SubjectTypeAdapter {
    SubjectTypeEnum isSupported();
    Boolean add(SubjectBO subjectBO);

    SubjectOptionBo query(Long subjectId);
}
