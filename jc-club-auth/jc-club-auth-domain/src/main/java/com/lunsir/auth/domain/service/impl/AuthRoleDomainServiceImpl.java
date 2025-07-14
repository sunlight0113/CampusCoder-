package com.lunsir.auth.domain.service.impl;

import com.lunsir.auth.domain.convert.AuthRoleBOConvert;
import com.lunsir.auth.domain.entity.AuthRoleBO;
import com.lunsir.auth.domain.service.AuthRoleDomainService;
import com.lunsir.auth.infra.entity.AuthRole;
import com.lunsir.auth.infra.service.AuthRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lunSir
 * @create 2024-09-28 16:11
 */
@Service
public class AuthRoleDomainServiceImpl implements AuthRoleDomainService {

    @Resource
    private AuthRoleService authRoleService;

    @Override
    public Boolean add(AuthRoleBO authRoleBO) {
        AuthRole authRole = AuthRoleBOConvert.INSTANCE.BOToEntity(authRoleBO);
        return authRoleService.save(authRole);
    }

    @Override
    public Boolean update(AuthRoleBO authRoleBO) {
        AuthRole authRole = AuthRoleBOConvert.INSTANCE.BOToEntity(authRoleBO);
        return authRoleService.updateById(authRole);
    }

    @Override
    public Boolean delete(AuthRoleBO authRoleBO) {
        AuthRole authRole = AuthRoleBOConvert.INSTANCE.BOToEntity(authRoleBO);
        return authRoleService.removeById(authRole.getId());
    }
}
