package com.satoken.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName sys_menu_role
 */
@TableName(value ="sys_menu_role")
@Data
public class SysMenuRole implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色id
     */
    @TableField(value = "role_id")
    private Integer roleId;

    /**
     * 菜单id
     */
    @TableField(value = "menu_id")
    private Integer menuId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}