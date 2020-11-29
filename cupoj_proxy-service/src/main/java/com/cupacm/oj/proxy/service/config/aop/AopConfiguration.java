package com.cupacm.oj.proxy.service.config.aop;

import com.cupacm.oj.proxy.common.annotation.RequestLogging;
import com.cupacm.oj.proxy.common.annotation.RpcResponseWrapper;
import com.cupacm.oj.proxy.common.interceptor.RpcResponseWrapperInterceptor;
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
public class AopConfiguration {

    private static final com.cupacm.oj.proxy.common.interceptor.LoggingInterceptor loggingInterceptor = new com.cupacm.oj.proxy.common.interceptor.LoggingInterceptor();

    private static final RpcResponseWrapperInterceptor rpcResponseWrapperInterceptor = new RpcResponseWrapperInterceptor();

    @Around("@annotation(logging)")
    public Object invoke(ProceedingJoinPoint proceedingJoinPoint, RequestLogging logging) throws Throwable {
        return loggingInterceptor.invoke(proceedingJoinPoint, logging);
    }

    @Around("@annotation(wrapper)")
    public Object invoke(ProceedingJoinPoint proceedingJoinPoint, RpcResponseWrapper wrapper) {
        return rpcResponseWrapperInterceptor.invoke(proceedingJoinPoint, wrapper);
    }
}
