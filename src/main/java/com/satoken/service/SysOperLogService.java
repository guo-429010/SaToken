package com.satoken.service;

import com.satoken.entity.SysOperLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author jiuho
* @description 针对表【sys_oper_log(操作日志记录)】的数据库操作Service
* @createDate 2023-10-16 11:11:16
*/
public interface SysOperLogService extends IService<SysOperLog> {

    void Insert(SysOperLog sysOperLog);

}
