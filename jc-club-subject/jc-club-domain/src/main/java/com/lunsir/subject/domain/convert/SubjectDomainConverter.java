package com.lunsir.subject.domain.convert;

import com.lunsir.subject.domain.entity.SubjectAnswerBO;
import com.lunsir.subject.domain.entity.SubjectBO;
import com.lunsir.subject.domain.entity.SubjectOptionBo;
import com.lunsir.subject.infra.basic.entity.SubjectInfo;
import com.lunsir.subject.infra.basic.entity.SubjectJudge;
import com.lunsir.subject.infra.basic.entity.SubjectMultiple;
import com.lunsir.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author lunSir
 * @create 2024-03-03 17:17
 */
@Mapper
public interface SubjectDomainConverter {
    SubjectDomainConverter INSTANCE = Mappers.getMapper(SubjectDomainConverter.class);


    SubjectInfo subjectBoToSubjectInfo(SubjectBO subjectBO);

    List<SubjectBO> SubjectInfoListToSubjectBOList(List<SubjectInfo> subjectInfoList);

    SubjectBO optionBoAndSubjectInfoToSubjectBo(SubjectOptionBo subjectOptionBo,SubjectInfo subjectInfo);


    List<SubjectAnswerBO> subjectJudgeListToSubjectAnswerBOList(List<SubjectJudge> subjectJudgeList);

    List<SubjectAnswerBO> subjectMultipleListToSubjectAnswerBOList(List<SubjectMultiple> subjectMultipleList);

    List<SubjectAnswerBO> subjectRadioListToSubjectAnswerBOList(List<SubjectRadio> subjectRadioList);
}
