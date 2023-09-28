package com.satoken.service;

import cn.dev33.satoken.util.SaResult;
import com.satoken.dto.UserDto;
import com.satoken.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author jiuho
* @description 针对表【sys_user】的数据库操作Service
* @createDate 2023-09-26 16:35:41
*/
public interface SysUserService extends IService<SysUser> {

    SaResult login(UserDto user);

    SaResult register(SysUser user);

    SaResult logout();

    SaResult resetPassword(Integer userId);
}
