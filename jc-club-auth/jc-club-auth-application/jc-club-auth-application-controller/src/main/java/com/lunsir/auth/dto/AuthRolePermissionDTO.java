package com.lunsir.auth.dto;


import lombok.Data;

import java.util.List;

@Data
public class AuthRolePermissionDTO {

    private Long id;
    //角色id
    private Long roleId;
    //权限id
    private Long permissionId;

    private List<Long> permissionIdList;
}

