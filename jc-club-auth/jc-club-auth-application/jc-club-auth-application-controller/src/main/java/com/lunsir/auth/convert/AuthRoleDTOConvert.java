package com.lunsir.auth.convert;

import com.lunsir.auth.domain.entity.AuthRoleBO;
import com.lunsir.auth.dto.AuthRoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lunSir
 * @create 2024-09-28 16:05
 */
@Mapper
public interface AuthRoleDTOConvert {
    AuthRoleDTOConvert INSTANCE = Mappers.getMapper(AuthRoleDTOConvert.class);
    AuthRoleBO DTOToBO(AuthRoleDTO authRoleDTO);
}
