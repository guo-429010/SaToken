package com.satoken.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.satoken.common.Constant;
import com.satoken.dto.UserDto;
import com.satoken.entity.*;
import com.satoken.service.*;
import com.satoken.mapper.SysUserMapper;
import com.satoken.vo.MenuVo;
import com.satoken.vo.UserVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
* @author jiuho
* @description 针对表【sys_user】的数据库操作Service实现
* @createDate 2023-09-26 16:35:41
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{

    @Value("${privateKey}")
    private String privateKey;

    @Value("${publicKey}")
    private String publicKey;

    @Resource
    private SysRoleUserService sysRoleUserService;

    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private SysMenuRoleService sysMenuRoleService;

    @Resource
    private SysMenuService sysMenuService;

    @Override
    public SaResult login(UserDto user) {
        SysUser sysUser = getOne(new QueryWrapper<SysUser>().eq("username", user.getUsername()));
        if (sysUser == null) {
            return SaResult.error("用户不存在");
        }
        if (!user.getPassword().equals(SaSecureUtil.rsaDecryptByPrivate(privateKey, sysUser.getPassword()))) {
            return SaResult.error("密码错误");
        }
        StpUtil.login(sysUser.getId());
        StpUtil.getSession().set("user", sysUser);
        UserVo userVo = UserVo.builder()
                .id(sysUser.getId())
                .username(sysUser.getUsername())
                .roles(StpUtil.getRoleList())
                .permissions(StpUtil.getPermissionList())
                .build();
        return SaResult.ok("登录成功").setData(userVo);
    }

    @Override
    public SaResult register(SysUser user) {
        SysUser sysUser = getOne(new QueryWrapper<SysUser>().eq("username", user.getUsername()));
        if (sysUser != null) {
            return SaResult.error("用户名已存在");
        }
        user.setPassword(SaSecureUtil.rsaEncryptByPublic(publicKey, user.getPassword()));
        save(user);
        SysRoleUser sysRoleUser = SysRoleUser.builder()
                .userId(user.getId())
                .roleId(Constant.Role_User)
                .build();
        sysRoleUserService.save(sysRoleUser);
        return SaResult.ok("注册成功");
    }

    @Override
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok("已退出登录");
    }

    @Override
    public SaResult resetPassword(Integer userId) {
        SysUser sysUser = getById(userId);
        if (sysUser == null) {
            return SaResult.error("用户不存在");
        }
        sysUser.setPassword(SaSecureUtil.rsaEncryptByPublic(publicKey, Constant.Default_Password));
        updateById(sysUser);
        return SaResult.ok("密码已重置");
    }

    @Override
    public SaResult getMenuList() {
        // 根据用户角色获取角色id
        List<Integer> roleIdList = StpUtil.getRoleList()
                .stream()
                .map(item -> sysRoleService.getOne(new QueryWrapper<SysRole>().eq("role_identify", item)).getId())
                .collect(Collectors.toList());
        // 根据角色id获取菜单
        Set<SysMenu> menuSet = new HashSet<>();
        roleIdList.forEach(id -> {
            List<SysMenu> list = sysMenuRoleService.list(new QueryWrapper<SysMenuRole>().eq("role_id", id))
                    .stream()
                    .map(SysMenuRole::getMenuId)
                    .map(m -> sysMenuService.getById(m))
                    .collect(Collectors.toList());
            menuSet.addAll(list);
        });
        List<SysMenu> menuList = new ArrayList<>(menuSet);
        List<MenuVo> subMenuList = sysMenuService.listSubMenu(menuList);
        for (MenuVo menu : subMenuList) {
            menu.setChildren(sysMenuService.listMenuItem(menu.getId(), menuList));
        }
        return SaResult.ok().setData(subMenuList);
    }
}



