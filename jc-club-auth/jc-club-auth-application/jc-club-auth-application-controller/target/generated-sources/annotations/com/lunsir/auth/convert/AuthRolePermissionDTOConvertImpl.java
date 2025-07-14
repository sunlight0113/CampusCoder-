package com.lunsir.auth.convert;

import com.lunsir.auth.domain.entity.AuthRolePermissionBO;
import com.lunsir.auth.dto.AuthRolePermissionDTO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-13T14:46:43+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_341 (Oracle Corporation)"
)
public class AuthRolePermissionDTOConvertImpl implements AuthRolePermissionDTOConvert {

    @Override
    public AuthRolePermissionBO DTOToBO(AuthRolePermissionDTO authRolePermissionDTO) {
        if ( authRolePermissionDTO == null ) {
            return null;
        }

        AuthRolePermissionBO authRolePermissionBO = new AuthRolePermissionBO();

        return authRolePermissionBO;
    }
}
