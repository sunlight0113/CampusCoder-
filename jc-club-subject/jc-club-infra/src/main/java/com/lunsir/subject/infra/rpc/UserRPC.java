package com.lunsir.subject.infra.rpc;

import com.lunsir.api.UserFeignService;
import com.lunsir.entity.AuthUserDto;
import com.lunsir.entity.Result;
import com.lunsir.subject.infra.rpc.entity.UserInfo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author lunSir
 * @create 2024-10-11 10:29
 */
@Component
public class UserRPC {
    @Resource
    private UserFeignService userFeignService;

    public UserInfo getUserInfo(String userName){
        AuthUserDto authUserDto = new AuthUserDto();
        authUserDto.setUserName(userName);
        Result<AuthUserDto> res = userFeignService.getUserInfo(authUserDto);
        UserInfo userInfo = new UserInfo();
        if (!res.getSuccess()){
            return userInfo;
        }
        AuthUserDto resDate = res.getDate();
        userInfo.setUserName(resDate.getUserName());
        userInfo.setId(resDate.getId());
        userInfo.setNickName(resDate.getNickName());
        userInfo.setAvatar(resDate.getAvatar());
        return userInfo;
    }
}
