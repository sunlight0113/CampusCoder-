package com.lunsir.application.subject.convert;

import com.lunsir.application.subject.dto.SubjectAnswerDTO;
import com.lunsir.application.subject.dto.SubjectDTO;
import com.lunsir.subject.domain.entity.SubjectAnswerBO;
import com.lunsir.subject.domain.entity.SubjectBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author lunSir
 * @create 2024-03-03 16:38
 */
@Mapper
public interface SubjectDTOConverter {
    SubjectDTOConverter INSTANCE = Mappers.getMapper(SubjectDTOConverter.class);

    // SubjectDTO 转换为 SubjectBO
    SubjectBO SubjectDTO_To_SubjectBO(SubjectDTO subjectDTO);

    // answerListDTO-->answerListBO
    List<SubjectAnswerBO> ANSWER_DTO_LIST_TO_ANSWER_BO_LIST(List<SubjectAnswerDTO> list);

    List<SubjectDTO> BOListToDTOList(List<SubjectBO> subjectBOList);

    List<SubjectAnswerDTO> AnswerBOToAnswerDTO(List<SubjectAnswerBO> list);
    // BO转DOT
    SubjectDTO BoToDTO(SubjectBO subjectBO);



}
