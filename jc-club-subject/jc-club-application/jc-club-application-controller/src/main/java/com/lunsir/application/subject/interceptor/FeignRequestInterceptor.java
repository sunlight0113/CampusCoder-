package com.lunsir.application.subject.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lunSir
 * @create 2024-10-11 11:53
 */
@Component
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 从RequestContextHolder中拿到requestAttributes
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // 拿到请求服务jc-club-subject的request
        HttpServletRequest request = requestAttributes.getRequest();
        // 因为请求服务jc-club-subject的request是经过网关的filter的
        // 所以可以拿到loginId
        String loginId = request.getHeader("loginId");
        if (StringUtils.isNotBlank(loginId)){
            // 放到去请求其他微服务的header中
            requestTemplate.header("loginId",loginId);
        }
    }
}
