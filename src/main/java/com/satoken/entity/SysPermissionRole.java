package com.satoken.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName sys_permission_role
 */
@TableName(value ="sys_permission_role")
@Data
public class SysPermissionRole implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 权限id
     */
    @TableField(value = "permission_id")
    private Integer permissionId;

    /**
     * 角色id
     */
    @TableField(value = "role_id")
    private Integer roleId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}