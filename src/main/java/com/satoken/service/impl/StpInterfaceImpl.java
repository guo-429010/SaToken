package com.satoken.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.satoken.entity.SysPermissionRole;
import com.satoken.entity.SysRole;
import com.satoken.entity.SysRoleUser;
import com.satoken.service.SysPermissionRoleService;
import com.satoken.service.SysPermissionService;
import com.satoken.service.SysRoleService;
import com.satoken.service.SysRoleUserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *  权限加载接口实现类
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private SysRoleUserService sysRoleUserService;

    @Resource
    private SysPermissionRoleService sysPermissionRoleService;

    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private SysPermissionService sysPermissionService;

    /**
     * 返回指定账号的权限列表
     * @param loginId
     * @param loginType
     * @return
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<Integer> list = getRoleList(loginId, loginType)
                .stream()
                .map(item -> sysRoleService.getOne(new QueryWrapper<SysRole>().eq("role_identify", item)).getId())
                .collect(Collectors.toList());
        Set<String> permissions = new HashSet<>();
        list.forEach(item -> {
            List<String> perList = sysPermissionRoleService.list(new QueryWrapper<SysPermissionRole>().eq("role_id", item))
                    .stream()
                    .map(SysPermissionRole::getPermissionId)
                    .map(p -> sysPermissionService.getById(p).getPermissionIdentify())
                    .collect(Collectors.toList());
            permissions.addAll(perList);
        });
        return new ArrayList<>(permissions);
    }

    /**
     *  返回指定账号的角色列表
     * @param loginId
     * @param loginType
     * @return
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
         return sysRoleUserService.list(new QueryWrapper<SysRoleUser>().eq("user_id", loginId))
                 .stream()
                 .map(item -> sysRoleService.getById(item.getRoleId()).getRoleIdentify())
                 .collect(Collectors.toList());
    }
}
