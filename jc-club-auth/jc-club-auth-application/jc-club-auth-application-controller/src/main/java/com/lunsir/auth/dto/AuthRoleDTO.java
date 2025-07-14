package com.lunsir.auth.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
    角色dto
 */
@Data
public class AuthRoleDTO implements Serializable {

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

