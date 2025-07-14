package com.lunsir.auth.interceptor;

import com.lunsir.auth.context.LoginContextHolder;
import org.apache.commons.lang.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lunSir
 * @create 2024-10-10 21:24
 */
public class LoginInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从header中拿到loginId，放到ThreadLocal中
        String loginId = request.getHeader("loginId");
        if (StringUtils.isNotBlank(loginId)){
            LoginContextHolder.put("loginId",loginId);
        }
        return true;
    }


    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        // 移除ThreadLocal中的数据
        LoginContextHolder.remove();
    }
}
