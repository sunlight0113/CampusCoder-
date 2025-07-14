package com.lunsir.subject.domain.convert;

import com.lunsir.subject.domain.entity.SubjectLabelBO;
import com.lunsir.subject.infra.basic.entity.SubjectLabel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author lunSir
 * @create 2024-03-03 14:00
 */
@Mapper
public interface SubjectLabelDomainConverter {

    SubjectLabelDomainConverter INSTANCE = Mappers.getMapper(SubjectLabelDomainConverter.class);

    // BO 转 Entity
    SubjectLabel BOToSubjectLabel(SubjectLabelBO subjectLabelBO);
    List<SubjectLabelBO> SubjectLabelListToBOList(List<SubjectLabel> subjectLabelList);

}
