package com.lunsir.auth.infra.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lunsir.auth.infra.mapper.AuthUserMapper;
import com.lunsir.auth.infra.entity.AuthUser;
import com.lunsir.auth.infra.service.AuthUserService;
import org.springframework.stereotype.Service;

/**
 * 用户信息表(AuthUser)表服务实现类
 *
 * @author makejava
 * @since 2024-09-27 19:02:13
 */
@Service
public class AuthUserServiceImpl extends ServiceImpl<AuthUserMapper, AuthUser> implements AuthUserService {

}

