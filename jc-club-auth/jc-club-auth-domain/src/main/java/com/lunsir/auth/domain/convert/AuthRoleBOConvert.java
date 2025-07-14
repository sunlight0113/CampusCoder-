package com.lunsir.auth.domain.convert;

import com.lunsir.auth.domain.entity.AuthRoleBO;
import com.lunsir.auth.domain.entity.AuthUserBO;
import com.lunsir.auth.infra.entity.AuthRole;
import com.lunsir.auth.infra.entity.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lunSir
 * @create 2024-09-28 16:05
 */
@Mapper
public interface AuthRoleBOConvert {
    AuthRoleBOConvert INSTANCE = Mappers.getMapper(AuthRoleBOConvert.class);
    AuthRole BOToEntity(AuthRoleBO authRoleBO);
}
