package com.lunsir.auth.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
    角色dto
 */
@Data
public class AuthRoleBO{

    private Long id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色唯一标识
     */
    private String roleKey;
    /**
     * 创建人
     */
    private String createdBy;
}

