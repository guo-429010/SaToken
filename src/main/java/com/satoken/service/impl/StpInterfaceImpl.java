package com.satoken.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import com.satoken.entity.SysRole;
import com.satoken.service.SysPermissionService;
import com.satoken.service.SysRoleService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  权限加载接口实现类
 */
@Component
public class StpInterfaceImpl implements StpInterface {


    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private SysPermissionService sysPermissionService;

    List<SysRole> roleList = new ArrayList<>();

    /**
     * 返回指定账号的权限列表
     * @param loginId
     * @param loginType
     * @return
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return sysPermissionService.getUserPermission(roleList.stream().map(SysRole::getId).collect(Collectors.toList()));
    }

    /**
     *  返回指定账号的角色列表
     * @param loginId
     * @param loginType
     * @return
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        roleList = sysRoleService.getUserRole(loginId);
        return roleList.stream().map(SysRole::getRoleIdentify).collect(Collectors.toList());
    }
}
