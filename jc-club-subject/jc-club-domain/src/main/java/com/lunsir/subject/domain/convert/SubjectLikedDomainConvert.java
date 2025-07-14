package com.lunsir.subject.domain.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lunSir
 * @create 2024-10-14 14:09
 */
@Mapper
public interface SubjectLikedDomainConvert {
    SubjectLikedDomainConvert INSTANCE = Mappers.getMapper(SubjectLikedDomainConvert.class);

}
