package com.cupacm.oj.proxy.integration.core;

import com.cupacm.oj.api.HelloService;
import com.cupacm.oj.api.SimService;
import com.cupacm.oj.api.StatisticsService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

@Component
public class CoreSoaConfig {
    @DubboReference
    private HelloService helloService;

    @DubboReference
    private SimService simService;

    @DubboReference(timeout = 60000)
    private StatisticsService statisticsService;
}
