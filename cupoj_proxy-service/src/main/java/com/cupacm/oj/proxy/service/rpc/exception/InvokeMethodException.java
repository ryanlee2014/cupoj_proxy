package com.cupacm.oj.proxy.service.rpc.exception;

import com.google.gson.Gson;

public class InvokeMethodException extends RuntimeException {
    public InvokeMethodException(String clazzName, String method, Object[] parameters) {
        super("Invoke: " + clazzName + "#" + method + " error. parameters: " + new Gson().toJson(parameters));
    }
}
