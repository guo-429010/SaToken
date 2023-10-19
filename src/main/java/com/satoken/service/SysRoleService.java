package com.satoken.service;

import com.satoken.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author jiuho
* @description 针对表【sys_role】的数据库操作Service
* @createDate 2023-09-27 13:40:39
*/
public interface SysRoleService extends IService<SysRole> {

    /**
     * 获取用户角色列表
     * @param loginId
     * @return
     */
    List<SysRole> getUserRole(Object loginId);
}
