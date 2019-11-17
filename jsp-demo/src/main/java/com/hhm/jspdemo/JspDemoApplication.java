package com.hhm.jspdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@MapperScan(basePackages = "com.hhm.jspdemo.mapper")
public class JspDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JspDemoApplication.class, args);
    }

}
