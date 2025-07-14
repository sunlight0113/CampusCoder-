package com.lunsir.auth.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.lunsir.auth.convert.AuthPermissionDTOConvert;
import com.lunsir.auth.domain.entity.AuthPermissionBO;
import com.lunsir.auth.domain.service.AuthPermissionDomainService;
import com.lunsir.auth.dto.AuthPermissionDTO;
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
 * @create 2024-09-28 17:01
 */
@RestController
@Slf4j
@RequestMapping("/permission")
public class AuthPermissionController {

    @Resource
    private AuthPermissionDomainService authPermissionDomainService;

    /**
     * 添加权限
     * @param authPermissionDTO
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody AuthPermissionDTO authPermissionDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("添加权限：AuthPermissionController.add.{}", JSON.toJSONString(authPermissionDTO));
            }
            // 校验参数
            Preconditions.checkArgument(StringUtils.isNotBlank(authPermissionDTO.getName()),"权限名称不能为空");
            Preconditions.checkArgument(StringUtils.isNotBlank(authPermissionDTO.getMenuUrl()),"菜单路由不能为空");
            Preconditions.checkNotNull(authPermissionDTO.getType(),"权限类型不能为空");
            Preconditions.checkArgument(StringUtils.isNotBlank(authPermissionDTO.getPermissionKey()),"权限的唯一标识不能为空");
            Preconditions.checkArgument(StringUtils.isNotBlank(authPermissionDTO.getIcon()),"权限图标不能为空");

            // DTO to BO
            AuthPermissionBO authPermissionBO = AuthPermissionDTOConvert.INSTANCE.DTOToBO(authPermissionDTO);
            // DomainService层
            return Result.ok(authPermissionDomainService.add(authPermissionBO));
        }catch (Exception e){
            log.error("添加权限：AuthPermissionController.add: {}",e.getMessage());
            return Result.fail(false,e.getMessage());
        }
    }


    /**
     * 更新权限
     * @param authPermissionDTO
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody AuthPermissionDTO authPermissionDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("更新权限：AuthPermissionController.update.{}", JSON.toJSONString(authPermissionDTO));
            }
            // 校验参数
            Preconditions.checkNotNull(authPermissionDTO.getId(),"权限ID不能为空");
            // DTO to BO
            AuthPermissionBO authPermissionBO = AuthPermissionDTOConvert.INSTANCE.DTOToBO(authPermissionDTO);
            // DomainService层
            return Result.ok(authPermissionDomainService.update(authPermissionBO));
        }catch (Exception e){
            log.error("更新权限：AuthPermissionController.update: {}",e.getMessage());
            return Result.fail(false,e.getMessage());
        }
    }

    /**
     * 删除权限
     * @param authPermissionDTO
     * @return
     */
    @PostMapping("/delete")
    public Result delete(@RequestBody AuthPermissionDTO authPermissionDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("删除权限：AuthPermissionController.delete.{}", JSON.toJSONString(authPermissionDTO));
            }
            // 校验参数
            Preconditions.checkNotNull(authPermissionDTO.getId(),"权限ID不能为空");
            // DTO to BO
            AuthPermissionBO authPermissionBO = AuthPermissionDTOConvert.INSTANCE.DTOToBO(authPermissionDTO);
            // DomainService层
            return Result.ok(authPermissionDomainService.delete(authPermissionBO));
        }catch (Exception e){
            log.error("删除权限：AuthPermissionController.delete: {}",e.getMessage());
            return Result.fail(false,e.getMessage());
        }
    }

}
