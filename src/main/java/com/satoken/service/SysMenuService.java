package com.satoken.service;

import com.satoken.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.satoken.vo.MenuVo;

import java.util.List;

/**
* @author jiuho
* @description 针对表【sys_menu】的数据库操作Service
* @createDate 2023-10-09 11:18:20
*/
public interface SysMenuService extends IService<SysMenu> {

    List<MenuVo> listSubMenu(List<SysMenu> menuList);

    List<MenuVo> listMenuItem(Integer menuParentId,List<SysMenu> menuList);
}
