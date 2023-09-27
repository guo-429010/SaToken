package com.satoken.controller;

import cn.dev33.satoken.util.SaResult;
import com.satoken.dto.UserDto;
import com.satoken.entity.SysUser;
import com.satoken.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
