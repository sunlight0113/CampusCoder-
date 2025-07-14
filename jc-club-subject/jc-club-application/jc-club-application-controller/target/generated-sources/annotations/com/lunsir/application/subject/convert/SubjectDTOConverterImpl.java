package com.lunsir.application.subject.convert;

import com.lunsir.application.subject.dto.SubjectAnswerDTO;
import com.lunsir.application.subject.dto.SubjectDTO;
import com.lunsir.subject.domain.entity.SubjectAnswerBO;
import com.lunsir.subject.domain.entity.SubjectBO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-14T22:18:37+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_341 (Oracle Corporation)"
)
public class SubjectDTOConverterImpl implements SubjectDTOConverter {

    @Override
    public SubjectBO SubjectDTO_To_SubjectBO(SubjectDTO subjectDTO) {
        if ( subjectDTO == null ) {
            return null;
        }

        SubjectBO subjectBO = new SubjectBO();

        subjectBO.setId( subjectDTO.getId() );
        subjectBO.setSubjectName( subjectDTO.getSubjectName() );
        subjectBO.setSubjectDifficult( subjectDTO.getSubjectDifficult() );
        subjectBO.setSettleName( subjectDTO.getSettleName() );
        subjectBO.setSubjectType( subjectDTO.getSubjectType() );
        subjectBO.setSubjectScore( subjectDTO.getSubjectScore() );
        subjectBO.setSubjectParse( subjectDTO.getSubjectParse() );
        List<Long> list = subjectDTO.getCategoryIds();
        if ( list != null ) {
            subjectBO.setCategoryIds( new ArrayList<Long>( list ) );
        }
        List<Long> list1 = subjectDTO.getLabelIds();
        if ( list1 != null ) {
            subjectBO.setLabelIds( new ArrayList<Long>( list1 ) );
        }
        subjectBO.setSubjectAnswer( subjectDTO.getSubjectAnswer() );
        subjectBO.setOptionList( ANSWER_DTO_LIST_TO_ANSWER_BO_LIST( subjectDTO.getOptionList() ) );
        subjectBO.setPageNo( subjectDTO.getPageNo() );
        subjectBO.setPageSize( subjectDTO.getPageSize() );
        List<String> list3 = subjectDTO.getLabelName();
        if ( list3 != null ) {
            subjectBO.setLabelName( new ArrayList<String>( list3 ) );
        }
        subjectBO.setLabelId( subjectDTO.getLabelId() );
        subjectBO.setCategoryId( subjectDTO.getCategoryId() );
        subjectBO.setKeyWord( subjectDTO.getKeyWord() );
        subjectBO.setCreatedBy( subjectDTO.getCreatedBy() );
        subjectBO.setCountNum( subjectDTO.getCountNum() );
        subjectBO.setUserName( subjectDTO.getUserName() );
        subjectBO.setAvatar( subjectDTO.getAvatar() );
        subjectBO.setLikedCount( subjectDTO.getLikedCount() );
        subjectBO.setLiked( subjectDTO.getLiked() );
        subjectBO.setNextSubject( subjectDTO.getNextSubject() );
        subjectBO.setLastSubject( subjectDTO.getLastSubject() );

        return subjectBO;
    }

    @Override
    public List<SubjectAnswerBO> ANSWER_DTO_LIST_TO_ANSWER_BO_LIST(List<SubjectAnswerDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<SubjectAnswerBO> list1 = new ArrayList<SubjectAnswerBO>( list.size() );
        for ( SubjectAnswerDTO subjectAnswerDTO : list ) {
            list1.add( subjectAnswerDTOToSubjectAnswerBO( subjectAnswerDTO ) );
        }

        return list1;
    }

    @Override
    public List<SubjectDTO> BOListToDTOList(List<SubjectBO> subjectBOList) {
        if ( subjectBOList == null ) {
            return null;
        }

        List<SubjectDTO> list = new ArrayList<SubjectDTO>( subjectBOList.size() );
        for ( SubjectBO subjectBO : subjectBOList ) {
            list.add( BoToDTO( subjectBO ) );
        }

        return list;
    }

    @Override
    public List<SubjectAnswerDTO> AnswerBOToAnswerDTO(List<SubjectAnswerBO> list) {
        if ( list == null ) {
            return null;
        }

        List<SubjectAnswerDTO> list1 = new ArrayList<SubjectAnswerDTO>( list.size() );
        for ( SubjectAnswerBO subjectAnswerBO : list ) {
            list1.add( subjectAnswerBOToSubjectAnswerDTO( subjectAnswerBO ) );
        }

        return list1;
    }

    @Override
    public SubjectDTO BoToDTO(SubjectBO subjectBO) {
        if ( subjectBO == null ) {
            return null;
        }

        SubjectDTO subjectDTO = new SubjectDTO();

        subjectDTO.setId( subjectBO.getId() );
        subjectDTO.setSubjectName( subjectBO.getSubjectName() );
        subjectDTO.setSubjectDifficult( subjectBO.getSubjectDifficult() );
        subjectDTO.setSettleName( subjectBO.getSettleName() );
        subjectDTO.setSubjectType( subjectBO.getSubjectType() );
        subjectDTO.setSubjectScore( subjectBO.getSubjectScore() );
        subjectDTO.setSubjectParse( subjectBO.getSubjectParse() );
        List<Long> list = subjectBO.getCategoryIds();
        if ( list != null ) {
            subjectDTO.setCategoryIds( new ArrayList<Long>( list ) );
        }
        List<Long> list1 = subjectBO.getLabelIds();
        if ( list1 != null ) {
            subjectDTO.setLabelIds( new ArrayList<Long>( list1 ) );
        }
        subjectDTO.setSubjectAnswer( subjectBO.getSubjectAnswer() );
        subjectDTO.setOptionList( AnswerBOToAnswerDTO( subjectBO.getOptionList() ) );
        List<String> list3 = subjectBO.getLabelName();
        if ( list3 != null ) {
            subjectDTO.setLabelName( new ArrayList<String>( list3 ) );
        }
        subjectDTO.setPageNo( subjectBO.getPageNo() );
        subjectDTO.setPageSize( subjectBO.getPageSize() );
        subjectDTO.setLabelId( subjectBO.getLabelId() );
        subjectDTO.setCategoryId( subjectBO.getCategoryId() );
        subjectDTO.setKeyWord( subjectBO.getKeyWord() );
        subjectDTO.setCountNum( subjectBO.getCountNum() );
        subjectDTO.setUserName( subjectBO.getUserName() );
        subjectDTO.setCreatedBy( subjectBO.getCreatedBy() );
        subjectDTO.setAvatar( subjectBO.getAvatar() );
        subjectDTO.setLikedCount( subjectBO.getLikedCount() );
        subjectDTO.setLiked( subjectBO.getLiked() );
        subjectDTO.setNextSubject( subjectBO.getNextSubject() );
        subjectDTO.setLastSubject( subjectBO.getLastSubject() );

        return subjectDTO;
    }

    protected SubjectAnswerBO subjectAnswerDTOToSubjectAnswerBO(SubjectAnswerDTO subjectAnswerDTO) {
        if ( subjectAnswerDTO == null ) {
            return null;
        }

        SubjectAnswerBO subjectAnswerBO = new SubjectAnswerBO();

        subjectAnswerBO.setOptionType( subjectAnswerDTO.getOptionType() );
        subjectAnswerBO.setOptionContent( subjectAnswerDTO.getOptionContent() );
        subjectAnswerBO.setIsCorrect( subjectAnswerDTO.getIsCorrect() );

        return subjectAnswerBO;
    }

    protected SubjectAnswerDTO subjectAnswerBOToSubjectAnswerDTO(SubjectAnswerBO subjectAnswerBO) {
        if ( subjectAnswerBO == null ) {
            return null;
        }

        SubjectAnswerDTO subjectAnswerDTO = new SubjectAnswerDTO();

        subjectAnswerDTO.setOptionType( subjectAnswerBO.getOptionType() );
        subjectAnswerDTO.setOptionContent( subjectAnswerBO.getOptionContent() );
        subjectAnswerDTO.setIsCorrect( subjectAnswerBO.getIsCorrect() );

        return subjectAnswerDTO;
    }
}
