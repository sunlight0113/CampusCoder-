package com.lunsir.application.subject.convert;

import com.lunsir.application.subject.dto.SubjectLikedDTO;
import com.lunsir.subject.domain.entity.SubjectLikedBO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-14T22:18:37+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_341 (Oracle Corporation)"
)
public class SubjectLikedDTOConvertImpl implements SubjectLikedDTOConvert {

    @Override
    public SubjectLikedBO dtoToBo(SubjectLikedDTO subjectLikedDTO) {
        if ( subjectLikedDTO == null ) {
            return null;
        }

        SubjectLikedBO subjectLikedBO = new SubjectLikedBO();

        subjectLikedBO.setId( subjectLikedDTO.getId() );
        subjectLikedBO.setSubjectId( subjectLikedDTO.getSubjectId() );
        subjectLikedBO.setLikeUserId( subjectLikedDTO.getLikeUserId() );
        subjectLikedBO.setStatus( subjectLikedDTO.getStatus() );
        subjectLikedBO.setSubjectName( subjectLikedDTO.getSubjectName() );
        subjectLikedBO.setPageNo( subjectLikedDTO.getPageNo() );
        subjectLikedBO.setPageSize( subjectLikedDTO.getPageSize() );

        return subjectLikedBO;
    }

    @Override
    public List<SubjectLikedDTO> boListToDtoList(List<SubjectLikedBO> subjectLikedBOList) {
        if ( subjectLikedBOList == null ) {
            return null;
        }

        List<SubjectLikedDTO> list = new ArrayList<SubjectLikedDTO>( subjectLikedBOList.size() );
        for ( SubjectLikedBO subjectLikedBO : subjectLikedBOList ) {
            list.add( subjectLikedBOToSubjectLikedDTO( subjectLikedBO ) );
        }

        return list;
    }

    protected SubjectLikedDTO subjectLikedBOToSubjectLikedDTO(SubjectLikedBO subjectLikedBO) {
        if ( subjectLikedBO == null ) {
            return null;
        }

        SubjectLikedDTO subjectLikedDTO = new SubjectLikedDTO();

        subjectLikedDTO.setId( subjectLikedBO.getId() );
        subjectLikedDTO.setSubjectId( subjectLikedBO.getSubjectId() );
        subjectLikedDTO.setLikeUserId( subjectLikedBO.getLikeUserId() );
        subjectLikedDTO.setStatus( subjectLikedBO.getStatus() );
        subjectLikedDTO.setPageNo( subjectLikedBO.getPageNo() );
        subjectLikedDTO.setPageSize( subjectLikedBO.getPageSize() );
        subjectLikedDTO.setSubjectName( subjectLikedBO.getSubjectName() );

        return subjectLikedDTO;
    }
}
