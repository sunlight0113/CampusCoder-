package com.lunsir.auth.domain.convert;

import com.lunsir.auth.domain.entity.AuthRoleBO;
import com.lunsir.auth.domain.entity.AuthRolePermissionBO;
import com.lunsir.auth.infra.entity.AuthRole;
import com.lunsir.auth.infra.entity.AuthRolePermission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lunSir
 * @create 2024-09-28 17:40
 */
@Mapper
public interface AuthRolePermissionBOConvert {
    AuthRolePermissionBOConvert INSTANCE = Mappers.getMapper(AuthRolePermissionBOConvert.class);
    AuthRolePermission BOToEntity(AuthRolePermissionBO authRolePermissionBO);
}
