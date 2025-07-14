package com.lunsir.auth.convert;

import com.lunsir.auth.domain.entity.AuthPermissionBO;
import com.lunsir.auth.domain.entity.AuthRolePermissionBO;
import com.lunsir.auth.dto.AuthPermissionDTO;
import com.lunsir.auth.dto.AuthRolePermissionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lunSir
 * @create 2024-09-28 17:37
 */
@Mapper
public interface AuthRolePermissionDTOConvert {
    AuthRolePermissionDTOConvert INSTANCE = Mappers.getMapper(AuthRolePermissionDTOConvert.class);
    AuthRolePermissionBO DTOToBO(AuthRolePermissionDTO authRolePermissionDTO);
}
