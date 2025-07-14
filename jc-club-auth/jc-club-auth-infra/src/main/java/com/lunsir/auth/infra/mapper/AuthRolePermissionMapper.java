package com.lunsir.auth.infra.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.lunsir.auth.infra.entity.AuthRolePermission;

/**
 * 角色权限关联表(AuthRolePermission)表数据库访问层
 *
 * @author makejava
 * @since 2024-09-28 17:35:15
 */
public interface AuthRolePermissionMapper extends BaseMapper<AuthRolePermission> {

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AuthRolePermission> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AuthRolePermission> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<AuthRolePermission> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<AuthRolePermission> entities);

}

