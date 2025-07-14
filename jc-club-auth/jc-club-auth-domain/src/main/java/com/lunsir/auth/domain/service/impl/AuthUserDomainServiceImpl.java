package com.lunsir.auth.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.gson.Gson;
import com.lunsir.auth.common.enums.DeletedFlagEnum;
import com.lunsir.auth.common.enums.StateFlagEnum;
import com.lunsir.auth.domain.Constants.AuthConstants;
import com.lunsir.auth.domain.convert.AuthUserBOConvert;
import com.lunsir.auth.domain.entity.AuthUserBO;
import com.lunsir.auth.domain.redis.RedisUtil;
import com.lunsir.auth.domain.service.AuthUserDomainService;
import com.lunsir.auth.infra.entity.*;
import com.lunsir.auth.infra.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import sun.security.provider.MD5;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lunSir
 * @create 2024-09-27 20:27
 */
@Service
public class AuthUserDomainServiceImpl implements AuthUserDomainService {

    @Resource
    private AuthUserService authUserService;

    @Resource
    private AuthUserRoleService authUserRoleService;

    @Resource
    private AuthRoleService authRoleService;

    @Resource
    private AuthRolePermissionService authRolePermissionService;

    @Resource
    private AuthPermissionService authPermissionService;

    @Resource
    private RedisUtil redisUtil;

    @Value("com.lunsir.md.salt")
    private String MD5Salt;

    private String authPermissionPrefix = "auth:permission";

    private String authRolePrefix = "auth:role";

    private final String LOGIN_KEY = "login";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean register(AuthUserBO authUserBO) {
        // 1. 查一下该用户有没有进行注册
        LambdaQueryWrapper<AuthUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AuthUser::getUserName,authUserBO.getUserName());
        int count = authUserService.count(queryWrapper);
        if (count > 0){
            // 说明该用户已经注册了
            return true;
        }
        // 2. 开始注册逻辑
        // Bo 转 Entity
        AuthUser authUser = AuthUserBOConvert.INSTANCE.BOToEntity(authUserBO);
        // 设置State 和 isDelete
        authUser.setIsDeleted(DeletedFlagEnum.NOT_DELETED.getCode());
        authUser.setStatus(StateFlagEnum.Start.getCode());
        if(StringUtils.isNotBlank(authUser.getPassword())){
            // 对密码进行MD5加密
            authUser.setPassword(SaSecureUtil.md5BySalt(authUser.getPassword(), MD5Salt));
        }
        // 调用infra基础设施层
        boolean saveFlag = authUserService.save(authUser);
        if (!saveFlag) return false;
        // 建立角色的关联
        LambdaQueryWrapper<AuthRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AuthRole::getRoleKey, AuthConstants.NORMAL_USER);
        AuthRole authRole = authRoleService.getOne(wrapper);
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setUserId(authUser.getId());
        authUserRole.setRoleId(authRole.getId());
        authUserRoleService.save(authUserRole);
        // 一  要把当前用户的角色都刷到我们的redis里面
        String roleKey = redisUtil.buildKey(authRolePrefix,authUser.getUserName());
        List<AuthRole> authRoleList = new LinkedList<>();
        authRoleList.add(authRole);
        redisUtil.set(roleKey,new Gson().toJson(authRoleList));
        // 二   把当前用户的权限刷到我们的缓存里面
        LambdaQueryWrapper<AuthRolePermission> authRolePermissionLambdaQueryWrapper = new LambdaQueryWrapper<>();
        authRolePermissionLambdaQueryWrapper.eq(AuthRolePermission::getRoleId,authRole.getId());
        List<AuthRolePermission> authRolePermissionList = authRolePermissionService.list(authRolePermissionLambdaQueryWrapper);
        // 拿到PermissionIdList
        List<Long> permissionIdList = authRolePermissionList.stream().map(t -> t.getPermissionId()).collect(Collectors.toList());
        // 去AuthPermission表中查
        List<AuthPermission> authPermissionList = authPermissionService.listByIds(permissionIdList);
        // 将authPermissionList放到Redis中
        String permissionKey = redisUtil.buildKey(authPermissionPrefix, authUser.getUserName());
        redisUtil.set(permissionKey,new Gson().toJson(authPermissionList));
        return true;
    }

    @Override
    public Boolean update(AuthUserBO authUserBO) {
        // Bo 转 Entity
        AuthUser authUser = AuthUserBOConvert.INSTANCE.BOToEntity(authUserBO);
        if (StringUtils.isNotBlank(authUser.getPassword())) {
            // 如果用户需要修改密码，我们需要进行加密
            // 对密码进行MD5加密
            authUser.setPassword(SaSecureUtil.md5BySalt(authUser.getPassword(), MD5Salt));
        }
        return authUserService.updateById(authUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(AuthUserBO authUserBO) {
        // Bo 转 Entity
        AuthUser authUser = AuthUserBOConvert.INSTANCE.BOToEntity(authUserBO);
        // 逻辑删除用户
        authUser.setIsDeleted(DeletedFlagEnum.DELETED.getCode());
        boolean b = authUserService.removeById(authUser);
        if (!b) return false;
        // 删除AuthUserRole中用户与角色的关系
        LambdaQueryWrapper<AuthUserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AuthUserRole::getUserId, authUser.getId());
        authUserRoleService.remove(wrapper);
        return true;
    }

    @Override
    public SaTokenInfo doLogin(String validCode) {
        // 从redis中拿openid
        String loginKey = redisUtil.buildKey(LOGIN_KEY, validCode);
        String openId = redisUtil.get(loginKey);
        if(StringUtils.isBlank(openId)){
            return null;
        }
        AuthUserBO authUserBO = new AuthUserBO();
        authUserBO.setUserName(openId);
        // 调用注册功能
        Boolean register = this.register(authUserBO);
        // 注册失败的情况
        if(!register) return null;
        // 注册成功
        StpUtil.login(openId);
        return StpUtil.getTokenInfo();
    }

    @Override
    public AuthUserBO getUserInfo(AuthUserBO authUserBO) {
        LambdaQueryWrapper<AuthUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AuthUser::getUserName,authUserBO.getUserName());
        AuthUser authUser = authUserService.list(wrapper).get(0);
        // entity to bo
        return AuthUserBOConvert.INSTANCE.entityToBo(authUser);
    }
}
