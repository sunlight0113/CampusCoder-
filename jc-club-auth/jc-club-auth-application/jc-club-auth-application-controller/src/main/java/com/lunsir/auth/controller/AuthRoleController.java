package com.lunsir.auth.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.lunsir.auth.convert.AuthRoleDTOConvert;
import com.lunsir.auth.domain.entity.AuthRoleBO;
import com.lunsir.auth.domain.service.AuthRoleDomainService;
import com.lunsir.auth.dto.AuthRoleDTO;
import com.lunsir.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lunSir
 * @create 2024-09-28 15:55
 */
@RestController
@RequestMapping("/role")
@Slf4j
public class AuthRoleController {

    @Resource
    private AuthRoleDomainService authRoleDomainService;

    /**
     * 添加角色
     * @param authRoleDTO
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody AuthRoleDTO authRoleDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("添加角色：AuthRoleController.add.{}", JSON.toJSONString(authRoleDTO));
            }
            // 校验参数
            Preconditions.checkArgument(StringUtils.isNotBlank(authRoleDTO.getRoleName()),"角色名称不能为空");
            Preconditions.checkArgument(StringUtils.isNotBlank(authRoleDTO.getRoleKey()),"角色唯一标识不能为空");
            // DTO to BO
            AuthRoleBO authRoleBO = AuthRoleDTOConvert.INSTANCE.DTOToBO(authRoleDTO);
            // DomainService层
            return Result.ok(authRoleDomainService.add(authRoleBO));
        }catch (Exception e){
            log.error("添加角色：AuthRoleController: {}",e.getMessage());
            return Result.fail(false,e.getMessage());
        }
    }


    /**
     * 更新角色
     * @param authRoleDTO
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody AuthRoleDTO authRoleDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("添加角色：AuthRoleController.update.{}", JSON.toJSONString(authRoleDTO));
            }
            // 校验参数
            Preconditions.checkNotNull(authRoleDTO.getId(),"角色id不能为空");
            // DTO to BO
            AuthRoleBO authRoleBO = AuthRoleDTOConvert.INSTANCE.DTOToBO(authRoleDTO);
            // DomainService层
            return Result.ok(authRoleDomainService.update(authRoleBO));
        }catch (Exception e){
            log.error("添加角色：AuthRoleController.update: {}",e.getMessage());
            return Result.fail(false,e.getMessage());
        }
    }

    /**
     * 删除角色
     * @param authRoleDTO
     * @return
     */
    @PostMapping("/delete")
    public Result delete(@RequestBody AuthRoleDTO authRoleDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("添加角色：AuthRoleController.delete.{}", JSON.toJSONString(authRoleDTO));
            }
            // 校验参数
            Preconditions.checkNotNull(authRoleDTO.getId(),"角色id不能为空");
            // DTO to BO
            AuthRoleBO authRoleBO = AuthRoleDTOConvert.INSTANCE.DTOToBO(authRoleDTO);
            // DomainService层
            return Result.ok(authRoleDomainService.delete(authRoleBO));
        }catch (Exception e){
            log.error("添加角色：AuthRoleController.delete: {}",e.getMessage());
            return Result.fail(false,e.getMessage());
        }
    }

}
