package com.lunsir.auth.domain.service;

import com.lunsir.auth.domain.entity.AuthRoleBO;
import org.springframework.context.annotation.Bean;

/**
 * @author lunSir
 * @create 2024-09-28 16:04
 */
public interface AuthRoleDomainService {
    Boolean add(AuthRoleBO authRoleBO);

    Boolean update(AuthRoleBO authRoleBO);

    Boolean delete(AuthRoleBO authRoleBO);
}
