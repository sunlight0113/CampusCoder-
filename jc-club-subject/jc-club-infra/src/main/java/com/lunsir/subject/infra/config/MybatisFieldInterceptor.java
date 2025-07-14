package com.lunsir.subject.infra.config;

import com.lunsir.subject.common.util.LoginUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;


/**
 * 拦截sql
 * 自动填充公共字段create_by,create_time,update_time
 *
 * @author lunSir
 * @create 2024-10-13 12:19
 */
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {
                MappedStatement.class, Object.class
        })
})
@Slf4j
public class MybatisFieldInterceptor implements Interceptor, InitializingBean {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        // 拿到当前执行的sql的类型
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        Object parameter = invocation.getArgs()[1];
        // 如果参数为空，直接放行
        if (Objects.isNull(parameter)) {
            invocation.proceed();
        }
        // 获取当前用户id
        String loginId = LoginUtil.getLoginId();
        // 如果用户id为空，说明请求不合法，放行
        if (StringUtils.isBlank(loginId)) {
            invocation.proceed();
        }
        // 拦截insert和update类型的sql
        if (SqlCommandType.INSERT == sqlCommandType || SqlCommandType.UPDATE == sqlCommandType) {
            replaceEntityProperties(loginId, parameter, sqlCommandType);
        }
        return invocation.proceed();
    }

    private void replaceEntityProperties(String loginId, Object parameter, SqlCommandType sqlCommandType) {
        if (parameter instanceof Map) {
            replaceMap(loginId, (Map) parameter, sqlCommandType);
        } else {
            // parameter为实体类型
            replace(loginId, parameter, sqlCommandType);
        }
    }

    private void replaceMap(String loginId, Map parameter, SqlCommandType sqlCommandType) {
        for (Object val : parameter.values()) {
            replace(loginId, val, sqlCommandType);
        }
    }

    private void replace(String loginId, Object parameter, SqlCommandType sqlCommandType) {
        if (SqlCommandType.INSERT == sqlCommandType) {
            dealInsert(loginId, parameter);
        } else {
            dealUpdate(loginId, parameter);
        }
    }

    /**
     * 插入逻辑（参数是实体类型）
     */
    private void dealInsert(String loginId, Object parameter) {
        // 反射运用
        // 获取实体的所有字段
        List<Field> fieldList = this.getAllFieldByEntity(parameter);
        for (Field field : fieldList) {
            // 处理一下异常，即便是出错也不要影响我们后面正常的业务逻辑
            try {
                // 设置可访问
                field.setAccessible(true);
                // 拿到parameter对应field字段的值
                Object val = field.get(parameter);
                // 如果这个字段不为空我们就放行
                if (Objects.nonNull(val)) {
                    field.setAccessible(false);
                    continue;
                }
                // 我们只处理为空的create_by,create_time等等
                if ("isDeleted".equals(field.getName())) {
                    field.set(parameter, 0);
                    field.setAccessible(false);
                } else if ("createdBy".equals(field.getName())) {
                    field.set(parameter, loginId);
                    field.setAccessible(false);
                } else if ("createdTime".equals(field.getName())) {
                    field.set(parameter, new Date());
                    field.setAccessible(false);
                } else {
                    field.setAccessible(false);
                }
            } catch (Exception e) {
                log.error("MybatisFieldInterceptor.dealInsert.error.{}", e.getMessage());
            }

        }
    }

    /**
     * 插入逻辑（参数是实体类型）
     */
    private void dealUpdate(String loginId, Object parameter) {
        // 反射运用
        // 获取实体的所有字段
        List<Field> fieldList = this.getAllFieldByEntity(parameter);
        for (Field field : fieldList) {
            // 处理一下异常，即便是出错也不要影响我们后面正常的业务逻辑
            try {
                // 设置可访问
                field.setAccessible(true);
                // 拿到parameter对应field字段的值
                Object val = field.get(parameter);
                // 如果这个字段不为空我们就放行
                if (Objects.nonNull(val)) {
                    field.setAccessible(false);
                    continue;
                }
                // 我们只处理为空的update_by,update_time等等
                if ("updateBy".equals(field.getName())) {
                    field.set(parameter, loginId);
                    field.setAccessible(false);
                } else if ("updateTime".equals(field.getName())) {
                    field.set(parameter, new Date());
                    field.setAccessible(false);
                } else {
                    field.setAccessible(false);
                }
            } catch (Exception e) {
                log.error("MybatisFieldInterceptor.dealUpdate.error.{}", e.getMessage());
            }

        }
    }

    /**
     * 通过反射获取一个实体所有的字段
     */
    List<Field> getAllFieldByEntity(Object entity) {
        List<Field> fieldList = new LinkedList<>();
        Class<?> clazz = entity.getClass();
        while (clazz != null) {
            Field[] fields = clazz.getDeclaredFields();
            List<Field> list = Arrays.asList(fields);
            fieldList.addAll(list);
            // 再找父对象的字段
            clazz = clazz.getSuperclass();
        }
        return fieldList;
    }

    @Override
    public Object plugin(Object target) {
        // 将这个拦截器注册包装进去
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        Interceptor.super.setProperties(properties);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 逻辑
    }
}
