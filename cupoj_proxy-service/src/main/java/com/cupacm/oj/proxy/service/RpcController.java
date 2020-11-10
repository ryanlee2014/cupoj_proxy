package com.cupacm.oj.proxy.service;

import com.cupacm.oj.proxy.service.rpc.RpcService;
import com.cupacm.oj.proxy.service.rpc.model.RpcRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RpcController {

    @Autowired
    private RpcService rpcService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String test() {
        return "Hello";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Object invokeRpcRequest(@RequestBody RpcRequest request) {
        return rpcService.invoke(request);
    }
}
