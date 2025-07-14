package com.lunsir.subject.domain.convert;

import com.lunsir.subject.domain.entity.SubjectAnswerBO;
import com.lunsir.subject.domain.entity.SubjectBO;
import com.lunsir.subject.domain.entity.SubjectOptionBo;
import com.lunsir.subject.infra.basic.entity.SubjectInfo;
import com.lunsir.subject.infra.basic.entity.SubjectJudge;
import com.lunsir.subject.infra.basic.entity.SubjectMultiple;
import com.lunsir.subject.infra.basic.entity.SubjectRadio;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-14T22:18:32+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_341 (Oracle Corporation)"
)
public class SubjectDomainConverterImpl implements SubjectDomainConverter {

    @Override
    public SubjectInfo subjectBoToSubjectInfo(SubjectBO subjectBO) {
        if ( subjectBO == null ) {
            return null;
        }

        SubjectInfo subjectInfo = new SubjectInfo();

        subjectInfo.setId( subjectBO.getId() );
        subjectInfo.setSubjectName( subjectBO.getSubjectName() );
        subjectInfo.setSubjectDifficult( subjectBO.getSubjectDifficult() );
        subjectInfo.setSettleName( subjectBO.getSettleName() );
        subjectInfo.setSubjectType( subjectBO.getSubjectType() );
        subjectInfo.setSubjectScore( subjectBO.getSubjectScore() );
        subjectInfo.setSubjectParse( subjectBO.getSubjectParse() );
        subjectInfo.setCreatedBy( subjectBO.getCreatedBy() );

        return subjectInfo;
    }

    @Override
    public List<SubjectBO> SubjectInfoListToSubjectBOList(List<SubjectInfo> subjectInfoList) {
        if ( subjectInfoList == null ) {
            return null;
        }

        List<SubjectBO> list = new ArrayList<SubjectBO>( subjectInfoList.size() );
        for ( SubjectInfo subjectInfo : subjectInfoList ) {
            list.add( subjectInfoToSubjectBO( subjectInfo ) );
        }

        return list;
    }

    @Override
    public SubjectBO optionBoAndSubjectInfoToSubjectBo(SubjectOptionBo subjectOptionBo, SubjectInfo subjectInfo) {
        if ( subjectOptionBo == null && subjectInfo == null ) {
            return null;
        }

        SubjectBO subjectBO = new SubjectBO();

        if ( subjectOptionBo != null ) {
            subjectBO.setSubjectAnswer( subjectOptionBo.getSubjectAnswer() );
            List<SubjectAnswerBO> list = subjectOptionBo.getOptionList();
            if ( list != null ) {
                subjectBO.setOptionList( new ArrayList<SubjectAnswerBO>( list ) );
            }
        }
        if ( subjectInfo != null ) {
            subjectBO.setId( subjectInfo.getId() );
            subjectBO.setSubjectName( subjectInfo.getSubjectName() );
            subjectBO.setSubjectDifficult( subjectInfo.getSubjectDifficult() );
            subjectBO.setSettleName( subjectInfo.getSettleName() );
            subjectBO.setSubjectType( subjectInfo.getSubjectType() );
            subjectBO.setSubjectScore( subjectInfo.getSubjectScore() );
            subjectBO.setSubjectParse( subjectInfo.getSubjectParse() );
            subjectBO.setCreatedBy( subjectInfo.getCreatedBy() );
        }

        return subjectBO;
    }

    @Override
    public List<SubjectAnswerBO> subjectJudgeListToSubjectAnswerBOList(List<SubjectJudge> subjectJudgeList) {
        if ( subjectJudgeList == null ) {
            return null;
        }

        List<SubjectAnswerBO> list = new ArrayList<SubjectAnswerBO>( subjectJudgeList.size() );
        for ( SubjectJudge subjectJudge : subjectJudgeList ) {
            list.add( subjectJudgeToSubjectAnswerBO( subjectJudge ) );
        }

        return list;
    }

    @Override
    public List<SubjectAnswerBO> subjectMultipleListToSubjectAnswerBOList(List<SubjectMultiple> subjectMultipleList) {
        if ( subjectMultipleList == null ) {
            return null;
        }

        List<SubjectAnswerBO> list = new ArrayList<SubjectAnswerBO>( subjectMultipleList.size() );
        for ( SubjectMultiple subjectMultiple : subjectMultipleList ) {
            list.add( subjectMultipleToSubjectAnswerBO( subjectMultiple ) );
        }

        return list;
    }

    @Override
    public List<SubjectAnswerBO> subjectRadioListToSubjectAnswerBOList(List<SubjectRadio> subjectRadioList) {
        if ( subjectRadioList == null ) {
            return null;
        }

        List<SubjectAnswerBO> list = new ArrayList<SubjectAnswerBO>( subjectRadioList.size() );
        for ( SubjectRadio subjectRadio : subjectRadioList ) {
            list.add( subjectRadioToSubjectAnswerBO( subjectRadio ) );
        }

        return list;
    }

    protected SubjectBO subjectInfoToSubjectBO(SubjectInfo subjectInfo) {
        if ( subjectInfo == null ) {
            return null;
        }

        SubjectBO subjectBO = new SubjectBO();

        subjectBO.setId( subjectInfo.getId() );
        subjectBO.setSubjectName( subjectInfo.getSubjectName() );
        subjectBO.setSubjectDifficult( subjectInfo.getSubjectDifficult() );
        subjectBO.setSettleName( subjectInfo.getSettleName() );
        subjectBO.setSubjectType( subjectInfo.getSubjectType() );
        subjectBO.setSubjectScore( subjectInfo.getSubjectScore() );
        subjectBO.setSubjectParse( subjectInfo.getSubjectParse() );
        subjectBO.setCreatedBy( subjectInfo.getCreatedBy() );

        return subjectBO;
    }

    protected SubjectAnswerBO subjectJudgeToSubjectAnswerBO(SubjectJudge subjectJudge) {
        if ( subjectJudge == null ) {
            return null;
        }

        SubjectAnswerBO subjectAnswerBO = new SubjectAnswerBO();

        subjectAnswerBO.setIsCorrect( subjectJudge.getIsCorrect() );

        return subjectAnswerBO;
    }

    protected SubjectAnswerBO subjectMultipleToSubjectAnswerBO(SubjectMultiple subjectMultiple) {
        if ( subjectMultiple == null ) {
            return null;
        }

        SubjectAnswerBO subjectAnswerBO = new SubjectAnswerBO();

        subjectAnswerBO.setOptionType( subjectMultiple.getOptionType() );
        subjectAnswerBO.setOptionContent( subjectMultiple.getOptionContent() );
        subjectAnswerBO.setIsCorrect( subjectMultiple.getIsCorrect() );

        return subjectAnswerBO;
    }

    protected SubjectAnswerBO subjectRadioToSubjectAnswerBO(SubjectRadio subjectRadio) {
        if ( subjectRadio == null ) {
            return null;
        }

        SubjectAnswerBO subjectAnswerBO = new SubjectAnswerBO();

        subjectAnswerBO.setOptionType( subjectRadio.getOptionType() );
        subjectAnswerBO.setOptionContent( subjectRadio.getOptionContent() );
        subjectAnswerBO.setIsCorrect( subjectRadio.getIsCorrect() );

        return subjectAnswerBO;
    }
}
