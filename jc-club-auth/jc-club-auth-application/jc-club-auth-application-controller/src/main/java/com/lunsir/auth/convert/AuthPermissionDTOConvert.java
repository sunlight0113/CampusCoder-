package com.lunsir.auth.convert;

import com.lunsir.auth.domain.entity.AuthPermissionBO;
import com.lunsir.auth.domain.entity.AuthRoleBO;
import com.lunsir.auth.dto.AuthPermissionDTO;
import com.lunsir.auth.dto.AuthRoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lunSir
 * @create 2024-09-28 17:03
 */
@Mapper
public interface AuthPermissionDTOConvert {
    AuthPermissionDTOConvert INSTANCE = Mappers.getMapper(AuthPermissionDTOConvert.class);
    AuthPermissionBO DTOToBO(AuthPermissionDTO authPermissionDTO);
}
