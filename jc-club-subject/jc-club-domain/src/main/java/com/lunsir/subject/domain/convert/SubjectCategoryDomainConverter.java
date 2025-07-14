package com.lunsir.subject.domain.convert;

import com.lunsir.subject.domain.entity.SubjectCategoryBO;
import com.lunsir.subject.infra.basic.entity.SubjectCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author lunSir
 * @create 2024-03-01 20:52
 */
@Mapper
public interface SubjectCategoryDomainConverter {
    SubjectCategoryDomainConverter INSTANCE = Mappers.getMapper(SubjectCategoryDomainConverter.class);
    SubjectCategory convertBoToSubjectCategory(SubjectCategoryBO subjectCategoryBO);

    List<SubjectCategoryBO> convertToSubjectCategoryBOList(List<SubjectCategory> subjectCategoryList);


}
