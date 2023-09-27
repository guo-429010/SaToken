package com.satoken.handler;

import cn.dev33.satoken.util.SaResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public SaResult handlerSaTokenException(Exception e) {
        log.error("[全局异常处理] 异常信息: {}", e.getMessage());
        return SaResult.error(e.getMessage());
    }
}
