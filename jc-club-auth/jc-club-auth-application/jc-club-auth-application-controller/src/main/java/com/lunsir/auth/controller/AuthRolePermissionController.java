package com.lunsir.auth.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.base.Preconditions;
import com.lunsir.auth.convert.AuthRolePermissionDTOConvert;
import com.lunsir.auth.domain.entity.AuthRolePermissionBO;
import com.lunsir.auth.domain.service.AuthRolePermissionDomainService;
import com.lunsir.auth.dto.AuthRolePermissionDTO;
import com.lunsir.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lunSir
 * @create 2024-09-28 17:39
 */
@RestController
@Slf4j
@RequestMapping("/rolePermission")
public class AuthRolePermissionController {

    @Resource
    private AuthRolePermissionDomainService authRolePermissionDomainService;


    /**
     * 新增角色权限
     * @param authRolePermissionDTO
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody AuthRolePermissionDTO authRolePermissionDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("新增角色权限：AuthRolePermissionController.add.{}", JSON.toJSONString(authRolePermissionDTO));
            }
            // 校验参数
            Preconditions.checkArgument(!CollectionUtils.isEmpty(authRolePermissionDTO.getPermissionIdList()),"权限关联不能为空");
            Preconditions.checkNotNull(authRolePermissionDTO.getRoleId(),"角色ID不能为空");

            // DTO to BO
            AuthRolePermissionBO authRolePermissionBO = AuthRolePermissionDTOConvert.INSTANCE.DTOToBO(authRolePermissionDTO);
            // DomainService层
            return Result.ok(authRolePermissionDomainService.add(authRolePermissionBO));
        }catch (Exception e){
            log.error("新增角色权限：AuthRolePermissionController.add: {}",e.getMessage());
            return Result.fail(false,e.getMessage());
        }
    }
}
