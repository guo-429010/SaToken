package com.satoken.service.impl;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.satoken.entity.SysLoginInfo;
import com.satoken.manager.AsyncManager;
import com.satoken.service.SysLoginInfoService;
import com.satoken.mapper.SysLoginInfoMapper;
import com.satoken.utils.AddressUtils;
import com.satoken.utils.IpUtils;
import com.satoken.utils.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.TimerTask;

/**
* @author jiuho
* @description 针对表【sys_login_info(登录日志表)】的数据库操作Service实现
* @createDate 2023-10-19 13:56:49
*/
@Slf4j
@Service
public class SysLoginInfoServiceImpl extends ServiceImpl<SysLoginInfoMapper, SysLoginInfo>
    implements SysLoginInfoService{

    @Override
    public void Insert(String username, Integer loginStatus, String msg) {
        String ip = IpUtils.getIpAddr();
        UserAgent ua = UserAgentUtil.parse(ServletUtils.getRequest().getHeader("User-Agent"));
        SysLoginInfo sysLoginInfo = new SysLoginInfo();
        sysLoginInfo.setUserName(username);
        sysLoginInfo.setLoginStatus(loginStatus);
        sysLoginInfo.setLoginMessage(msg);
        sysLoginInfo.setLoginIp(ip);
        sysLoginInfo.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
        sysLoginInfo.setLoginBrowser(ua.getBrowser().toString());
        sysLoginInfo.setLoginOs(ua.getOs().toString());
        sysLoginInfo.setLoginTime(new Date());
        AsyncManager.execute(new TimerTask() {
            @Override
            public void run() {
                boolean b = save(sysLoginInfo);
                if (!b) {
                    log.error("【登录日志记录】保存失败");
                } else {
                    log.info("【登录日志记录】保存成功");
                }
            }
        });
    }
}




