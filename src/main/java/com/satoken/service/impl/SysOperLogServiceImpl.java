package com.satoken.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.satoken.entity.SysOperLog;
import com.satoken.manager.AsyncManager;
import com.satoken.service.SysOperLogService;
import com.satoken.mapper.SysOperLogMapper;
import com.satoken.utils.AddressUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.TimerTask;

/**
* @author jiuho
* @description 针对表【sys_oper_log(操作日志记录)】的数据库操作Service实现
* @createDate 2023-10-16 11:11:16
*/
@Slf4j
@Service
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLog>
    implements SysOperLogService{

    @Override
    public void Insert(SysOperLog sysOperLog) {
        AsyncManager.execute(new TimerTask() {
            @Override
            public void run() {
                sysOperLog.setOperLocation(AddressUtils.getRealAddressByIP(sysOperLog.getOperIp()));
                boolean b = save(sysOperLog);
                if (!b) {
                    log.error("【操作日志记录】保存失败");
                } else {
                    log.info("【操作日志记录】保存成功");
                }
            }
        });
    }
}




