package com.satoken.service;

import com.satoken.entity.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author jiuho
* @description 针对表【sys_permission】的数据库操作Service
* @createDate 2023-09-27 13:40:39
*/
public interface SysPermissionService extends IService<SysPermission> {

    /**
     * 获取用户权限列表
     * @param roleIds
     * @return
     */
    List<String> getUserPermission(List<Integer> roleIds);
}
