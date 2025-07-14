package com.lunsir.application.subject.convert;

import com.lunsir.application.subject.controller.SubjectLabelController;
import com.lunsir.application.subject.dto.SubjectLabelDTO;
import com.lunsir.subject.domain.entity.SubjectLabelBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author lunSir
 * @create 2024-03-03 13:53
 */
@Mapper
public interface SubjectLabelDTOConverter {
    SubjectLabelDTOConverter INSTANCE = Mappers.getMapper(SubjectLabelDTOConverter.class);

    // DTO转BO
    SubjectLabelBO DTOToSubjectLabelBO(SubjectLabelDTO subjectLabelDTO);
    List<SubjectLabelDTO> DTO_LIST_To_SubjectLabelBO_LIST(List<SubjectLabelBO> subjectLabelBOList);

}
