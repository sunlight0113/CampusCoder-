package com.lunsir.api;

import com.lunsir.entity.AuthUserDto;
import com.lunsir.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author lunSir
 * @create 2024-10-11 10:15
 */
@FeignClient("jc-club-auth")
public interface UserFeignService {
    @PostMapping("/user/getUserInfo")
    Result<AuthUserDto> getUserInfo(@RequestBody AuthUserDto authUserDto);
}
