package com.lunsir.auth.infra.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (AuthRole)表实体类
 *
 * @author makejava
 * @since 2024-09-28 16:13:51
 */
@Data
public class AuthRole extends Model<AuthRole> {

    private Long id;
    //角色名称
    private String roleName;
    //角色唯一标识
    private String roleKey;
    //创建人
    private String createdBy;
    //创建时间
    private Date createdTime;
    //更新人
    private String updateBy;
    //更新时间
    private Date updateTime;
    //是否被删除 0未删除 1已删除
    private Integer isDeleted;
}

