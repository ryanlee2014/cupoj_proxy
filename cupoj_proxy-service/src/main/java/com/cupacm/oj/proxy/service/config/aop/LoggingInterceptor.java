package com.cupacm.oj.proxy.service.config.aop;

import com.cupacm.oj.proxy.common.annotation.RequestLogging;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Aspect
@Order(1)
public class LoggingInterceptor {
    @Around("@annotation(logging)")
    public Object invoke(ProceedingJoinPoint proceedingJoinPoint, RequestLogging logging) throws Throwable {
        return new com.cupacm.oj.proxy.common.interceptor.LoggingInterceptor().invoke(proceedingJoinPoint, logging);
    }
}
