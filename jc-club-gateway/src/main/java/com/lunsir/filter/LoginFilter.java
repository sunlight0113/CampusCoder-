package com.lunsir.filter;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author lunSir
 * @create 2024-10-10 20:58
 */
@Component
@Slf4j
public class LoginFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();
        // 把登录接口给放行了
        String path = request.getURI().getPath();
        // 打印日志
        log.info("LoginFilter.filter.url:{}",path);
        if ("/user/doLogin".equals(path)){
            // 放行
            return chain.filter(exchange);
        }
        // 拿到tokenInfo
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        // 打印日志
        if (log.isInfoEnabled()){
            log.info("LoginFilter.filter.tokenInfo:{}",new Gson().toJson(tokenInfo));
        }
        // 将loginId放到header中
        String loginId = (String) tokenInfo.getLoginId();
        mutate.header("loginId",loginId);
        return chain.filter(exchange.mutate().request(mutate.build()).build());
    }
}
