package com.lunsir.auth.domain.convert;

import com.lunsir.auth.domain.entity.AuthUserBO;
import com.lunsir.auth.infra.entity.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lunSir
 * @create 2024-09-27 20:35
 */
@Mapper
public interface AuthUserBOConvert {
    AuthUserBOConvert INSTANCE = Mappers.getMapper(AuthUserBOConvert.class);
    AuthUser BOToEntity(AuthUserBO authUserBO);
    AuthUserBO entityToBo(AuthUser authUser);
}
