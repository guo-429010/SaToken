package com.satoken.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Slf4j
@Component
public class ShutdownManager {

    @PreDestroy
    public void destroy() {
        try {
            log.info("关闭后台任务线程池...");
            AsyncManager.shutdown();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
