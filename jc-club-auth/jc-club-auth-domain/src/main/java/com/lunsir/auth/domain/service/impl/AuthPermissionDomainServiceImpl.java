package com.lunsir.auth.domain.service.impl;

import com.lunsir.auth.common.enums.StateFlagEnum;
import com.lunsir.auth.domain.convert.AuthPermissionBOConvert;
import com.lunsir.auth.domain.entity.AuthPermissionBO;
import com.lunsir.auth.domain.service.AuthPermissionDomainService;
import com.lunsir.auth.infra.entity.AuthPermission;
import com.lunsir.auth.infra.service.AuthPermissionService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lunSir
 * @create 2024-09-28 16:58
 */
@Service
public class AuthPermissionDomainServiceImpl implements AuthPermissionDomainService {

    @Resource
    private AuthPermissionService authPermissionService;

    @Override
    public Boolean add(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = AuthPermissionBOConvert.INSTANCE.BOToEntity(authPermissionBO);
        if (authPermission.getStatus() == null) {
            // 权限默认启用
            authPermission.setStatus(StateFlagEnum.Start.getCode());
        }
        return authPermissionService.save(authPermission);
    }

    @Override
    public Boolean update(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = AuthPermissionBOConvert.INSTANCE.BOToEntity(authPermissionBO);
        return authPermissionService.updateById(authPermission);
    }

    @Override
    public Boolean delete(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = AuthPermissionBOConvert.INSTANCE.BOToEntity(authPermissionBO);
        return authPermissionService.removeById(authPermission.getId());
    }
}
