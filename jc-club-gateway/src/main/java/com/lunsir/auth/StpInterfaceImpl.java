package com.lunsir.auth;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.temp.SaTempInterface;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.lunsir.entity.AuthPermission;
import com.lunsir.entity.AuthRole;
import com.lunsir.redis.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lunSir
 * @create 2024-10-02 17:58
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private RedisUtil redisUtil;

    private String authPermissionPrefix = "auth.permission";

    private String authRolePrefix = "auth:role";

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        String key = redisUtil.buildKey(authPermissionPrefix, loginId.toString());
        String keyVal = redisUtil.get(key);
        if(StringUtils.isBlank(keyVal)){
            return Collections.emptyList();
        }
        List<AuthPermission> permissionList = new Gson().fromJson(keyVal,new TypeToken<List<AuthPermission>>(){}.getType());
        List<String> permissionKeyList = permissionList.stream().map(AuthPermission::getPermissionKey).collect(Collectors.toList());
        return permissionKeyList;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        String key = redisUtil.buildKey(authRolePrefix, loginId.toString());
        String keyVal = redisUtil.get(key);
        if(StringUtils.isBlank(keyVal)){
            return Collections.emptyList();
        }
        List<AuthRole> roleList = new Gson().fromJson(keyVal,new TypeToken<List<AuthRole>>(){}.getType());
        List<String> roleKeyList = roleList.stream().map(AuthRole::getRoleKey).collect(Collectors.toList());
        return roleKeyList;
    }
}
