package com.cupacm.oj.proxy.main.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(
        scanBasePackages = {
                "com.cupacm.oj.proxy"
        },
        exclude = {
                DataSourceAutoConfiguration.class,
                HibernateJpaAutoConfiguration.class
        }
)
public class ProxyBootStrap {
        public static void main(String[] args) {
                ApplicationContext context = SpringApplication.run(ProxyBootStrap.class, args);
        }
}
