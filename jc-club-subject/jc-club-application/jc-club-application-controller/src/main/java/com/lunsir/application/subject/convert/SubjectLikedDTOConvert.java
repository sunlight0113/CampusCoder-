package com.lunsir.application.subject.convert;

import com.lunsir.application.subject.dto.SubjectLikedDTO;
import com.lunsir.subject.domain.entity.SubjectLikedBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author lunSir
 * @create 2024-10-14 14:25
 */
@Mapper
public interface SubjectLikedDTOConvert {
    SubjectLikedDTOConvert INSTANCE = Mappers.getMapper(SubjectLikedDTOConvert.class);
    // DTO to BO
    SubjectLikedBO dtoToBo(SubjectLikedDTO subjectLikedDTO);

    List<SubjectLikedDTO> boListToDtoList(List<SubjectLikedBO> subjectLikedBOList);
}
