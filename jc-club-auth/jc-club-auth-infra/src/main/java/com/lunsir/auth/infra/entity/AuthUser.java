package com.lunsir.auth.infra.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息表(AuthUser)表实体类
 *
 * @author makejava
 * @since 2024-09-27 19:02:11
 */
@Data
public class AuthUser extends Model<AuthUser> {
    //主键
    private Long id;
    //用户名称/账号
    private String userName;
    //昵称
    private String nickName;
    //邮箱
    private String email;
    //手机号
    private String phone;
    //密码
    private String password;
    //性别
    private Integer sex;
    //头像
    private String avatar;
    //状态 0启用 1禁用
    private Integer status;
    //个人介绍
    private String introduce;
    //特殊字段
    private String extJson;
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
    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
    }

