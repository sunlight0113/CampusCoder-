package com.lunsir.auth.convert;

import com.lunsir.auth.domain.entity.AuthUserBO;
import com.lunsir.entity.AuthUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lunSir
 * @create 2024-09-27 20:29
 */
@Mapper
public interface AuthUserDTOConvert {
    AuthUserDTOConvert INSTANCE = Mappers.getMapper(AuthUserDTOConvert.class);
    AuthUserBO dtoToBo(AuthUserDto authUserDto);
    AuthUserDto boToDto(AuthUserBO authUserBO);
}
