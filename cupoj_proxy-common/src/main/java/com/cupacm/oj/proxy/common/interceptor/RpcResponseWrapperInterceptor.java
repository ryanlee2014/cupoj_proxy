package com.cupacm.oj.proxy.common.interceptor;

import com.cupacm.oj.proxy.common.annotation.RpcResponseWrapper;
import com.cupacm.oj.proxy.common.dto.RpcResponseDto;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

@Slf4j
public class RpcResponseWrapperInterceptor {
    private static final Gson gson = new Gson();

    @Around("@annotation(wrapper)")
    public Object invoke(ProceedingJoinPoint proceedingJoinPoint, RpcResponseWrapper wrapper) {
        try {
            Object response = proceedingJoinPoint.proceed();
            return buildSuccessResponse(response);
        }
        catch (Throwable e) {
            return buildFailResponse(e);
        }
    }

    public <T> RpcResponseDto<T> buildSuccessResponse(T response) {
        RpcResponseDto<T> dto = new RpcResponseDto<>();
        dto.setData(response);
        dto.setSuccess(true);
        return dto;
    }

    public <T> RpcResponseDto<T> buildFailResponse(Throwable throwable) {
        RpcResponseDto<T> dto = new RpcResponseDto<>();
        dto.setException(throwable.getMessage());
        dto.setSuccess(false);
        return dto;
    }
}
