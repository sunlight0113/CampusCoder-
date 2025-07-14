package com.lunsir.entity;

import lombok.Data;

/**
 * 用户信息表(AuthUser)表实体类
 *
 * @author makejava
 * @since 2024-09-27 19:02:11
 */
@Data
public class AuthUserDto{
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
}

