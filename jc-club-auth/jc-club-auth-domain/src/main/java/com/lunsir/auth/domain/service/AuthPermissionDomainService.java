package com.lunsir.auth.domain.service;

import com.lunsir.auth.domain.entity.AuthPermissionBO;

/**
 * @author lunSir
 * @create 2024-09-28 16:58
 */
public interface AuthPermissionDomainService {
    Boolean add(AuthPermissionBO authPermissionBO);

    Boolean update(AuthPermissionBO authPermissionBO);

    Boolean delete(AuthPermissionBO authPermissionBO);
}
