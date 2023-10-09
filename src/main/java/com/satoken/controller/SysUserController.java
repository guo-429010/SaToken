package com.satoken.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.util.SaResult;
import com.satoken.dto.UserDto;
import com.satoken.entity.SysUser;
import com.satoken.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public SaResult resetPassword(@PathVariable(value = "userId") Integer userId) {
        return sysUserService.resetPassword(userId);
    }

    @GetMapping("/menuList")
    public SaResult menuList() {
        return sysUserService.getMenuList();
    }
}
