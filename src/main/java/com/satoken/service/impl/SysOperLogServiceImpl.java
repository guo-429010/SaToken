package com.satoken.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.satoken.entity.SysOperLog;
import com.satoken.service.SysOperLogService;
import com.satoken.mapper.SysOperLogMapper;
import org.springframework.stereotype.Service;

/**
* @author jiuho
* @description 针对表【sys_oper_log(操作日志记录)】的数据库操作Service实现
* @createDate 2023-10-16 11:11:16
*/
@Service
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLog>
    implements SysOperLogService{

}




