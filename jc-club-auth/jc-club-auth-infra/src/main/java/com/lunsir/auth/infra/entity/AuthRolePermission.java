package com.lunsir.auth.infra.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色权限关联表(AuthRolePermission)表实体类
 *
 * @author makejava
 * @since 2024-09-28 17:35:15
 */
@Data
public class AuthRolePermission extends Model<AuthRolePermission> {

    private Long id;
    //角色id
    private Long roleId;
    //权限id
    private Long permissionId;
    //创建人
    private String createdBy;
    //创建时间
    private Date createdTime;
    //更新人
    private String updateBy;
    //更新时间
    private Date updateTime;

    private Integer isDeleted;
}

