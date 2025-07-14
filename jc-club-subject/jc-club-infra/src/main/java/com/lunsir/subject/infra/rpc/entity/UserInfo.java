package com.lunsir.subject.infra.rpc.entity;

import lombok.Data;

/**
 * @author lunSir
 * @create 2024-10-11 10:31
 */
@Data
public class UserInfo {
    //主键
    private Long id;
    //用户名称/账号
    private String userName;
    //昵称
    private String nickName;
    // 用户头像
    private String avatar;

}
