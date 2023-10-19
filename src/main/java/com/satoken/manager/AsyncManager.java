package com.satoken.manager;

import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class AsyncManager {

    /**
     * 操作延迟时间
     */
    private static final int DELAY_TIME = 1000;

    private static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

    public static void execute(TimerTask task) {
        scheduledExecutorService.schedule(task, DELAY_TIME, TimeUnit.MILLISECONDS);
    }

    public static void shutdown() {
        scheduledExecutorService.shutdown();
    }
}
