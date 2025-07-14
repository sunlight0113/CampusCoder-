package com.lunsir.auth.convert;

import com.lunsir.auth.domain.entity.AuthPermissionBO;
import com.lunsir.auth.dto.AuthPermissionDTO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-13T14:46:43+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_341 (Oracle Corporation)"
)
public class AuthPermissionDTOConvertImpl implements AuthPermissionDTOConvert {

    @Override
    public AuthPermissionBO DTOToBO(AuthPermissionDTO authPermissionDTO) {
        if ( authPermissionDTO == null ) {
            return null;
        }

        AuthPermissionBO authPermissionBO = new AuthPermissionBO();

        return authPermissionBO;
    }
}
