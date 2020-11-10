package com.cupacm.oj.proxy.integration.core;

import com.cupacm.oj.api.HelloService;
import com.cupacm.oj.api.SimService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

@Component
public class CoreSoaConfig {
    @DubboReference
    private HelloService helloService;

    @DubboReference
    private SimService simService;
}
