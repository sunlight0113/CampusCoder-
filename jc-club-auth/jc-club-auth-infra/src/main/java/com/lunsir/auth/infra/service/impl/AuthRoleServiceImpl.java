package com.lunsir.auth.infra.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lunsir.auth.infra.mapper.AuthRoleMapper;
import com.lunsir.auth.infra.entity.AuthRole;
import com.lunsir.auth.infra.service.AuthRoleService;
import org.springframework.stereotype.Service;

/**
 * (AuthRole)表服务实现类
 *
 * @author makejava
 * @since 2024-09-28 16:13:51
 */
@Service
public class AuthRoleServiceImpl extends ServiceImpl<AuthRoleMapper, AuthRole> implements AuthRoleService {

}

