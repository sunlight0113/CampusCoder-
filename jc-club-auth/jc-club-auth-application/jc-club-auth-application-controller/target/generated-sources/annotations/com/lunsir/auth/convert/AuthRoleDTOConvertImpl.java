package com.lunsir.auth.convert;

import com.lunsir.auth.domain.entity.AuthRoleBO;
import com.lunsir.auth.dto.AuthRoleDTO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-13T14:46:43+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_341 (Oracle Corporation)"
)
public class AuthRoleDTOConvertImpl implements AuthRoleDTOConvert {

    @Override
    public AuthRoleBO DTOToBO(AuthRoleDTO authRoleDTO) {
        if ( authRoleDTO == null ) {
            return null;
        }

        AuthRoleBO authRoleBO = new AuthRoleBO();

        return authRoleBO;
    }
}
