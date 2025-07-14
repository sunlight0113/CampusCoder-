package com.lunsir.auth.infra.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lunsir.auth.infra.mapper.AuthRolePermissionMapper;
import com.lunsir.auth.infra.entity.AuthRolePermission;
import com.lunsir.auth.infra.service.AuthRolePermissionService;
import org.springframework.stereotype.Service;

/**
 * 角色权限关联表(AuthRolePermission)表服务实现类
 *
 * @author makejava
 * @since 2024-09-28 17:35:15
 */
@Service("authRolePermissionService")
public class AuthRolePermissionServiceImpl extends ServiceImpl<AuthRolePermissionMapper, AuthRolePermission> implements AuthRolePermissionService {

}

