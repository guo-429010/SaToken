package com.satoken.service;

import com.satoken.entity.SysLoginInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author jiuho
* @description 针对表【sys_login_info(登录日志表)】的数据库操作Service
* @createDate 2023-10-19 13:56:49
*/
public interface SysLoginInfoService extends IService<SysLoginInfo> {

    void Insert(String username, Integer loginStatus, String msg);
}
