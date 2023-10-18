package com.satoken.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.util.SaResult;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.satoken.annotation.OperLog;
import com.satoken.dto.UserDto;
import com.satoken.entity.SysUser;
import com.satoken.enums.BusinessType;
import com.satoken.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Slf4j
@RestController
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;


    @PostMapping("/login")
    public SaResult login(@RequestBody UserDto user) {
        return sysUserService.login(user);
    }

    @PostMapping("/register")
    public SaResult register(@RequestBody SysUser user) {
        return sysUserService.register(user);
    }

    @GetMapping("/logout")
    public SaResult logout() {
        return sysUserService.logout();
    }

    @GetMapping("/resetPassword/{userId}")
    @SaCheckRole("admin")
    @OperLog(title = "重置密码", businessType = BusinessType.UPDATE)
    public SaResult resetPassword(@PathVariable(value = "userId") Integer userId) {
        return sysUserService.resetPassword(userId);
    }

    @GetMapping("/menuList")
    @OperLog(title = "获取菜单列表", businessType = BusinessType.QUERY)
    public SaResult menuList() {
        return sysUserService.getMenuList();
    }

    @PostMapping("/test")
    @OperLog(title = "测试", businessType = BusinessType.INSERT)
    public SaResult test(@RequestBody SysUser sysUser) {
        return SaResult.ok().setData(new Date());
    }

}
