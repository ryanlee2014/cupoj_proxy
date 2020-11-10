package com.cupacm.oj.proxy.service.rpc.exception;

public class ClassNotFoundException extends RuntimeException {
    public ClassNotFoundException(String clazzName) {
        super("Class: " + clazzName + " not found.");
    }
}
