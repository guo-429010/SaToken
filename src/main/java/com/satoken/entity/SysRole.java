package com.satoken.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @TableName sys_role
 */
@TableName(value ="sys_role")
@Data
public class SysRole implements Serializable {
    /**
     * 角色id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色标识
     */
    @TableField(value = "role_identify")
    private String roleIdentify;

    /**
     * 角色名称
     */
    @TableField(value = "role_name")
    private String roleName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
