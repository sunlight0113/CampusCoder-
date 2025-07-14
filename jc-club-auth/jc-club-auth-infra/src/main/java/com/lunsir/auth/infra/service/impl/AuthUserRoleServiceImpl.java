package com.lunsir.auth.infra.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lunsir.auth.infra.mapper.AuthUserRoleMapper;
import com.lunsir.auth.infra.entity.AuthUserRole;
import com.lunsir.auth.infra.service.AuthUserRoleService;
import org.springframework.stereotype.Service;

/**
 * 用户角色表(AuthUserRole)表服务实现类
 *
 * @author makejava
 * @since 2024-09-28 20:26:01
 */
@Service("authUserRoleService")
public class AuthUserRoleServiceImpl extends ServiceImpl<AuthUserRoleMapper, AuthUserRole> implements AuthUserRoleService {

}

