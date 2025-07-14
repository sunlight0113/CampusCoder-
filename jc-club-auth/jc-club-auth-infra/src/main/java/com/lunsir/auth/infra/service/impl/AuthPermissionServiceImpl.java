package com.lunsir.auth.infra.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lunsir.auth.infra.mapper.AuthPermissionMapper;
import com.lunsir.auth.infra.entity.AuthPermission;
import com.lunsir.auth.infra.service.AuthPermissionService;
import org.springframework.stereotype.Service;

/**
 * (AuthPermission)表服务实现类
 *
 * @author makejava
 * @since 2024-09-28 16:55:51
 */
@Service("authPermissionService")
public class AuthPermissionServiceImpl extends ServiceImpl<AuthPermissionMapper, AuthPermission> implements AuthPermissionService {

}

