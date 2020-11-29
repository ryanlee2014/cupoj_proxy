package com.cupacm.oj.proxy.service.rpc;


import com.cupacm.oj.proxy.common.annotation.RequestLogging;
import com.cupacm.oj.proxy.common.util.SpringContextUtil;
import com.cupacm.oj.proxy.service.rpc.exception.InvokeMethodException;
import com.cupacm.oj.proxy.service.rpc.exception.MethodNotFoundException;
import com.cupacm.oj.proxy.service.rpc.exception.ClassNotFoundException;
import com.cupacm.oj.proxy.service.rpc.model.RpcRequest;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@Slf4j
public class RpcService {

    @RequestLogging
    public Object invoke(RpcRequest request) {
        String clazzName = request.getClassName();
        String methodName = request.getMethodName();
        List<String> parameters = request.getArguments();
        Class<?> clazz = getClassByName(clazzName);
        ApplicationContext context = SpringContextUtil.context();
        Object instance = context.getBean(clazz);
        Method[] methods = clazz.getMethods();
        Method targetMethod = null;
        for (Method method : methods) {
            if (Objects.equals(method.getName(), methodName)) {
                targetMethod = method;
                break;
            }
        }

        if (Objects.isNull(targetMethod)) {
            throw new MethodNotFoundException(clazzName, methodName);
        }

        Class<?>[] parameterTypes = targetMethod.getParameterTypes();
        List<Object> targetParameters = Lists.newArrayList();
        Gson gson = new Gson();
        for(int i = 0; i < parameterTypes.length; ++i) {
            Class<?> parameterClazz = parameterTypes[i];
            String jsonBody = parameters.get(i);
            Object targetPayload = gson.fromJson(jsonBody, parameterClazz);
            targetParameters.add(targetPayload);
        }

        return innerInvoke(instance, targetMethod, targetParameters.toArray());
    }

    private Class<?> getClassByName(String clazzName) {
        Class<?> clazz;
        try {
            clazz = Class.forName(clazzName);
        } catch (Exception e) {
            log.error("getClassByName exception.", e);
            throw new ClassNotFoundException(clazzName);
        }
        return clazz;
    }

    private Object innerInvoke(Object instance, Method method, Object[] parameters) {
        try {
            return method.invoke(instance, parameters.length > 0 ? parameters : null);
        }
        catch (Exception e) {
            log.error("innerInvoke exception. ", e);
            throw new InvokeMethodException(instance.getClass().getSimpleName(), method.getName(), parameters);
        }
    }
}
