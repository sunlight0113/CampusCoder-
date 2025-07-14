package com.lunsir.auth.domain.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 角色权限关联表(AuthRolePermission)表实体类
 *
 * @author makejava
 * @since 2024-09-28 17:35:15
 */
@Data
public class AuthRolePermissionBO{

    private Long id;
    //角色id
    private Long roleId;
    //权限id
    private Long permissionId;

    private List<Long> permissionIdList;
}

