package com.lunsir.auth.domain.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.lunsir.auth.domain.entity.AuthUserBO;

/**
 * @author lunSir
 * @create 2024-09-27 20:27
 */
public interface AuthUserDomainService {
    Boolean register(AuthUserBO authUserBO);

    Boolean update(AuthUserBO authUserBO);

    Boolean delete(AuthUserBO authUserBO);

    SaTokenInfo doLogin(String validCode);

    AuthUserBO getUserInfo(AuthUserBO authUserBO);
}
