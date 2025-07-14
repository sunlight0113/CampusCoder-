package com.lunsir.auth.convert;

import com.lunsir.auth.domain.entity.AuthUserBO;
import com.lunsir.entity.AuthUserDto;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-13T14:46:43+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_341 (Oracle Corporation)"
)
public class AuthUserDTOConvertImpl implements AuthUserDTOConvert {

    @Override
    public AuthUserBO dtoToBo(AuthUserDto authUserDto) {
        if ( authUserDto == null ) {
            return null;
        }

        AuthUserBO authUserBO = new AuthUserBO();

        authUserBO.setId( authUserDto.getId() );
        authUserBO.setUserName( authUserDto.getUserName() );
        authUserBO.setNickName( authUserDto.getNickName() );
        authUserBO.setEmail( authUserDto.getEmail() );
        authUserBO.setPhone( authUserDto.getPhone() );
        authUserBO.setPassword( authUserDto.getPassword() );
        authUserBO.setSex( authUserDto.getSex() );
        authUserBO.setAvatar( authUserDto.getAvatar() );
        authUserBO.setStatus( authUserDto.getStatus() );
        authUserBO.setIntroduce( authUserDto.getIntroduce() );
        authUserBO.setExtJson( authUserDto.getExtJson() );
        authUserBO.setCreatedBy( authUserDto.getCreatedBy() );

        return authUserBO;
    }

    @Override
    public AuthUserDto boToDto(AuthUserBO authUserBO) {
        if ( authUserBO == null ) {
            return null;
        }

        AuthUserDto authUserDto = new AuthUserDto();

        authUserDto.setId( authUserBO.getId() );
        authUserDto.setUserName( authUserBO.getUserName() );
        authUserDto.setNickName( authUserBO.getNickName() );
        authUserDto.setEmail( authUserBO.getEmail() );
        authUserDto.setPhone( authUserBO.getPhone() );
        authUserDto.setPassword( authUserBO.getPassword() );
        authUserDto.setSex( authUserBO.getSex() );
        authUserDto.setAvatar( authUserBO.getAvatar() );
        authUserDto.setStatus( authUserBO.getStatus() );
        authUserDto.setIntroduce( authUserBO.getIntroduce() );
        authUserDto.setExtJson( authUserBO.getExtJson() );
        authUserDto.setCreatedBy( authUserBO.getCreatedBy() );

        return authUserDto;
    }
}
