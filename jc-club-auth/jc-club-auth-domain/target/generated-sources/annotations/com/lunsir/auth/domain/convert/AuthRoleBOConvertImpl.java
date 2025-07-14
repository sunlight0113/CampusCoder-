package com.lunsir.auth.domain.convert;

import com.lunsir.auth.domain.entity.AuthRoleBO;
import com.lunsir.auth.infra.entity.AuthRole;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-13T14:46:39+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_341 (Oracle Corporation)"
)
public class AuthRoleBOConvertImpl implements AuthRoleBOConvert {

    @Override
    public AuthRole BOToEntity(AuthRoleBO authRoleBO) {
        if ( authRoleBO == null ) {
            return null;
        }

        AuthRole authRole = new AuthRole();

        authRole.setId( authRoleBO.getId() );
        authRole.setRoleName( authRoleBO.getRoleName() );
        authRole.setRoleKey( authRoleBO.getRoleKey() );
        authRole.setCreatedBy( authRoleBO.getCreatedBy() );

        return authRole;
    }
}
