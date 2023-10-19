package com.satoken.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 登录日志表
 * @TableName sys_login_info
 */
@TableName(value ="sys_login_info")
@Data
public class SysLoginInfo implements Serializable {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 登录用户名
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 登录状态 (0=成功,1=失败)
     */
    @TableField(value = "login_status")
    private Integer loginStatus;

    /**
     * 登录地址
     */
    @TableField(value = "login_ip")
    private String loginIp;

    /**
     * 登录地址
     */
    @TableField(value = "login_location")
    private String loginLocation;

    /**
     * 浏览器
     */
    @TableField(value = "login_browser")
    private String loginBrowser;

    /**
     * 操作系统
     */
    @TableField(value = "login_os")
    private String loginOs;

    /**
     * 登录消息
     */
    @TableField(value = "login_message")
    private String loginMessage;

    /**
     * 登录时间
     */
    @TableField(value = "login_time")
    private Date loginTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
