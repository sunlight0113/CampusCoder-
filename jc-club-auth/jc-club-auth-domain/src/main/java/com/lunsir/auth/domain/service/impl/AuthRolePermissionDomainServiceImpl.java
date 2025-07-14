package com.lunsir.auth.domain.service.impl;

import com.lunsir.auth.domain.convert.AuthRolePermissionBOConvert;
import com.lunsir.auth.domain.entity.AuthRolePermissionBO;
import com.lunsir.auth.domain.service.AuthRolePermissionDomainService;
import com.lunsir.auth.infra.entity.AuthRolePermission;
import com.lunsir.auth.infra.service.AuthRolePermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lunSir
 * @create 2024-09-28 17:41
 */
@Service
public class AuthRolePermissionDomainServiceImpl implements AuthRolePermissionDomainService {

    @Resource
    private AuthRolePermissionService authRolePermissionService;

    @Override
    public Boolean add(AuthRolePermissionBO authRolePermissionBO) {
        List<AuthRolePermission> authRolePermissionList = new ArrayList<>();
        Long roleId = authRolePermissionBO.getRoleId();
        authRolePermissionBO.getPermissionIdList().forEach(permissionId->{
            AuthRolePermission authRolePermission = new AuthRolePermission();
            authRolePermission.setPermissionId(permissionId);
            authRolePermission.setRoleId(roleId);
            authRolePermissionList.add(authRolePermission);
        });
        return authRolePermissionService.saveBatch(authRolePermissionList);
    }
}
