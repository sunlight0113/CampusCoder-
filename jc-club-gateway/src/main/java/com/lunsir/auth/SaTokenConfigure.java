package com.lunsir.auth;

import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * [Sa-Token 权限认证] 配置类
 * @author click33
 */
@Configuration
public class SaTokenConfigure {
    // 注册 Sa-Token全局过滤器
    @Bean
    public SaReactorFilter getSaReactorFilter() {
        return new SaReactorFilter()
                // 拦截地址
                .addInclude("/**")    /* 拦截全部path */
                // 开放地址
                .addExclude("/favicon.ico")
                // 鉴权方法：每次访问进入
                .setAuth(obj -> {
                    SaRouter.match("/auth/**", "/auth/user/doLogin", r -> StpUtil.checkRole("normal_user"));
                    SaRouter.match("/oss/**",r->StpUtil.checkLogin());
                    //SaRouter.match("/subject/add",r->StpUtil.checkPermission("subject:add"));
                    SaRouter.match("/subject/**",r->StpUtil.checkLogin());
                });
    }
}

