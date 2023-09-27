package com.satoken.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @TableName sys_permission
 */
@TableName(value ="sys_permission")
@Data
public class SysPermission implements Serializable {
    /**
     * 权限id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 权限标识
     */
    @TableField(value = "permission_identify")
    private String permissionIdentify;

    /**
     * 权限名称
     */
    @TableField(value = "permission_name")
    private String permissionName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
