package com.lunsir.auth.domain.convert;

import com.lunsir.auth.domain.entity.AuthPermissionBO;
import com.lunsir.auth.infra.entity.AuthPermission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lunSir
 * @create 2024-09-28 16:59
 */
@Mapper
public interface AuthPermissionBOConvert {
    AuthPermissionBOConvert INSTANCE = Mappers.getMapper(AuthPermissionBOConvert.class);

    AuthPermission BOToEntity(AuthPermissionBO authPermissionBO);
}
