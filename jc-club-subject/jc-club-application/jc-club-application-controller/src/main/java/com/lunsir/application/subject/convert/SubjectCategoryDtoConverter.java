package com.lunsir.application.subject.convert;

import com.lunsir.application.subject.dto.SubjectCategoryDTO;
import com.lunsir.application.subject.dto.SubjectLabelDTO;
import com.lunsir.subject.domain.entity.SubjectCategoryBO;
import com.lunsir.subject.domain.entity.SubjectLabelBO;
import com.lunsir.subject.infra.basic.entity.SubjectCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author lunSir
 * @create 2024-03-02 15:30
 */
@Mapper
public interface SubjectCategoryDtoConverter {
    SubjectCategoryDtoConverter INSTANCE = Mappers.getMapper(SubjectCategoryDtoConverter.class);
    SubjectCategoryBO SubjectCategoryDtoToSubjectCategoryBO(SubjectCategoryDTO subjectCategoryDTO);
    List<SubjectCategoryDTO> convertSubjectCategoryDTOList(List<SubjectCategoryBO> categoryBOList);

    SubjectCategoryDTO boToDTO(SubjectCategoryBO subjectCategoryBO);
}
