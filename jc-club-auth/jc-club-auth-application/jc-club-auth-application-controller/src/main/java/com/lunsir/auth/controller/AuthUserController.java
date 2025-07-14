package com.lunsir.auth.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.lunsir.auth.convert.AuthUserDTOConvert;
import com.lunsir.auth.domain.entity.AuthUserBO;
import com.lunsir.auth.domain.service.AuthUserDomainService;
import com.lunsir.entity.AuthUserDto;
import com.lunsir.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author lunSir
 * @create 2024-09-27 19:17
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class AuthUserController {

    @Resource
    private AuthUserDomainService authUserDomainService;

    /**
     * 注册用户
     * @param authUserDto
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody AuthUserDto authUserDto){
        try {
            // 打印日志
            if (log.isInfoEnabled()){
                log.info("注册用户：AuthUserController.register.{}",JSON.toJSONString(authUserDto));
            }
            // 校验参数
            Preconditions.checkArgument(StringUtils.isNotBlank(authUserDto.getUserName()),"用户名不能为空");
            Preconditions.checkArgument(StringUtils.isNotBlank(authUserDto.getPassword()),"密码不能为空");
            Preconditions.checkArgument(StringUtils.isNotBlank(authUserDto.getEmail()),"邮箱不能为空");
            // Dto转Bo
            AuthUserBO authUserBO = AuthUserDTOConvert.INSTANCE.dtoToBo(authUserDto);
            // 调用Domain层
            return Result.ok(authUserDomainService.register(authUserBO));
        }catch (Exception e){
            log.error("注册用户：AuthUserController: {}",e.getMessage());
            return Result.fail(false,e.getMessage());
        }
    }

    /**
     * 更新用户信息
     * @param authUserDto
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody AuthUserDto authUserDto){
        try {
            if (log.isInfoEnabled()){
                log.info("更新用户：AuthUserController.update.{}",JSON.toJSONString(authUserDto));
            }
            // 检验参数
            Preconditions.checkNotNull(authUserDto.getId(),"用户id不能为空");
            // Dto转BO
            AuthUserBO authUserBO = AuthUserDTOConvert.INSTANCE.dtoToBo(authUserDto);
            // DomainService层
            return Result.ok(authUserDomainService.update(authUserBO));
        }catch (Exception e){
            log.error("更新用户失败：AuthUserController.update.{}",JSON.toJSONString(authUserDto));
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 删除用户信息
     * @param authUserDto
     * @return
     */
    @PostMapping("/delete")
    public Result delete(@RequestBody AuthUserDto authUserDto) {
        try {
            if (log.isInfoEnabled()){
                log.info("删除用户：AuthUserController.delete.{}",JSON.toJSONString(authUserDto));
            }
            // 检验参数
            Preconditions.checkNotNull(authUserDto.getId(),"用户id不能为空");
            // Dto转BO
            AuthUserBO authUserBO = AuthUserDTOConvert.INSTANCE.dtoToBo(authUserDto);
            // DomainService层
            return Result.ok(authUserDomainService.delete(authUserBO));
        }catch (Exception e){
            log.error("删除用户失败：AuthUserController.delete.{}",JSON.toJSONString(authUserDto));
            return Result.fail(e.getMessage());
        }
    }


    /**
     * 用户禁用启用功能
     * @param authUserDto
     * @return
     */
    @PostMapping("/changeStatus")
    public Result changeStatus(@RequestBody AuthUserDto authUserDto){
        try {
            if (log.isInfoEnabled()){
                log.info("用户禁用启用功能：AuthUserController.changeStatus.{}",JSON.toJSONString(authUserDto));
            }
            // 检验参数
            Preconditions.checkNotNull(authUserDto.getId(),"用户id不能为空");
            Preconditions.checkNotNull(authUserDto.getStatus(),"用户状态不能为空");
            // Dto转BO
            AuthUserBO authUserBO = AuthUserDTOConvert.INSTANCE.dtoToBo(authUserDto);
            // DomainService层
            return Result.ok(authUserDomainService.update(authUserBO));
        }catch (Exception e){
            log.error("用户禁用启用功能失败：AuthUserController.changeStatus.{}",JSON.toJSONString(authUserDto));
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 用户登录
     */
    @RequestMapping("/doLogin")
    public Result doLogin(@RequestParam("validCode") String validCode){
        try {
            if (log.isInfoEnabled()){
                log.info("用户登录功能：AuthUserController.doLogin.{}",JSON.toJSONString(validCode));
            }
            // 检验参数
            Preconditions.checkArgument(StringUtils.isNotBlank(validCode),"验证码不能为空！");
            SaTokenInfo saTokenInfo = authUserDomainService.doLogin(validCode);
            if (saTokenInfo == null) return Result.fail("登录失败");
            return Result.ok(saTokenInfo);
        }catch (Exception e){
            log.error("用户登录失败：AuthUserController.doLogin.{}",JSON.toJSONString(validCode));
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 查询个人详情信息
     */
    @PostMapping("/getUserInfo")
    public Result<AuthUserDto> getUserInfo(@RequestBody AuthUserDto authUserDto){
        try {
            // 打印日志
            if (log.isInfoEnabled()){
                log.info("查询个人详情信息：AuthUserController.getUserInfo:{}",JSON.toJSONString(authUserDto));
            }
            // 校验参数
            Preconditions.checkArgument(StringUtils.isNotBlank(authUserDto.getUserName()),"用户名不能为空！");
            // dto to bo
            AuthUserBO authUserBO = AuthUserDTOConvert.INSTANCE.dtoToBo(authUserDto);
            // bo 转 dto
            AuthUserDto res = AuthUserDTOConvert.INSTANCE.boToDto(authUserDomainService.getUserInfo(authUserBO));
            return Result.ok(res);
        }catch (Exception e){
            log.error("查询个人详情信息失败：AuthUserController.getUserInfo:{}",JSON.toJSONString(authUserDto));
            return Result.fail(null,e.getMessage());
        }
    }

    /**
     * 用户退出登录功能
     * @param userName
     * @return
     */
    @RequestMapping("/logOut")
    public Result logout(@RequestParam("userName") String userName){
        // 这里由于没有做Json序列化，就不需要加if(log.isInfoEnabled)判断了
        log.info("用户退出登录功能：AuthUserController.logOut.{}",userName);
        // 校验参数
        Preconditions.checkArgument(StringUtils.isNotBlank(userName),"用户名（openid）不能为空！");
        StpUtil.logout(userName);
        return Result.ok();
    }

}
