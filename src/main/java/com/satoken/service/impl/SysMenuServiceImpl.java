package com.satoken.service.impl;

import cn.dev33.satoken.util.SaFoxUtil;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.satoken.entity.SysMenu;
import com.satoken.service.SysMenuService;
import com.satoken.mapper.SysMenuMapper;
import com.satoken.vo.MenuVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author jiuho
* @description 针对表【sys_menu】的数据库操作Service实现
* @createDate 2023-10-09 11:18:20
*/
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
    implements SysMenuService{

    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> userMenuList(List<Integer> roleIdList) {
        return sysMenuMapper.selectUserMenuList(roleIdList);
    }

    /**
     * 获取目录列表
     * @param menuList
     * @return
     */
    @Override
    public List<MenuVo> listSubMenu(List<SysMenu> menuList) {
        return menuList.stream()
            .filter(menu -> SaFoxUtil.isEmpty(menu.getMenuParentId()))
            .map(menu -> BeanUtil.copyProperties(menu, MenuVo.class))
            .collect(Collectors.toList());
    }

    /**
     * 获取目录下的子菜单
     * @param menuParentId
     * @return
     */
    @Override
    public List<MenuVo> listMenuItem(Integer menuParentId,List<SysMenu> menuList) {
        return menuList.stream()
                .filter(menu -> SaFoxUtil.isNotEmpty(menu.getMenuParentId()))
                .filter(menu -> menu.getMenuParentId().equals(menuParentId))
                .map(menu -> {
                    if (SaFoxUtil.isEmpty(menu.getMenuPath())) {
                        MenuVo menuVo = BeanUtil.copyProperties(menu, MenuVo.class);
                        menuVo.setChildren(listMenuItem(menuVo.getId(), menuList));
                        return menuVo;
                    }
                    return BeanUtil.copyProperties(menu, MenuVo.class);
                })
                .collect(Collectors.toList());
    }
}




