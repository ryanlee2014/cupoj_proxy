package com.cupacm.oj.proxy.service.rpc.exception;

public class MethodNotFoundException extends RuntimeException {
    public MethodNotFoundException (String clazzName, String methodName) {
        super("Class: " + clazzName + ", methodName: " + methodName + " not found");
    }
}
