package com.satoken.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName sys_menu
 */
@TableName(value ="sys_menu")
@Data
public class SysMenu implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 菜单名称
     */
    @TableField(value = "menu_name")
    private String menuName;

    /**
     * 菜单路由
     */
    @TableField(value = "menu_path")
    private String menuPath;

    /**
     * 菜单路径
     */
    @TableField(value = "menu_component")
    private String menuComponent;

    /**
     * 菜单图标
     */
    @TableField(value = "menu_icon")
    private String menuIcon;

    /**
     * 父级菜单id
     */
    @TableField(value = "menu_parent_id")
    private Integer menuParentId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}