package com.satoken.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.satoken.dto.UserDto;
import com.satoken.entity.SysUser;
import com.satoken.service.SysUserService;
import com.satoken.mapper.SysUserMapper;
import com.satoken.vo.UserVo;
import org.springframework.stereotype.Service;


/**
* @author jiuho
* @description 针对表【sys_user】的数据库操作Service实现
* @createDate 2023-09-26 16:35:41
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{

    @Override
    public SaResult login(UserDto user) {
        SysUser sysUser = getOne(new QueryWrapper<SysUser>().eq("username", user.getUsername()));
        if (sysUser == null) {
            return SaResult.error("用户不存在");
        }
        if (!sysUser.getPassword().equals(user.getPassword())) {
            return SaResult.error("密码错误");
        }
        StpUtil.login(sysUser.getId());
        StpUtil.getSession().set("user", sysUser);
        return SaResult.ok("登录成功").setData(BeanUtil.copyProperties(sysUser, UserVo.class));
    }

    @Override
    public SaResult register(SysUser user) {
        SysUser sysUser = getOne(new QueryWrapper<SysUser>().eq("username", user.getUsername()));
        if (sysUser != null) {
            return SaResult.error("用户名已存在");
        }
        save(user);
        return SaResult.ok("注册成功").setData(BeanUtil.copyProperties(user, UserVo.class));
    }
}




