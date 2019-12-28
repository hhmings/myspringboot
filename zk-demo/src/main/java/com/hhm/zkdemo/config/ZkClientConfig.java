package com.hhm.zkdemo.config;

import com.hhm.zkdemo.zk.ZkClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author houhaimin
 * @version 1.0
 * @date 2019/12/26 23:38
 */
@Configuration
public class ZkClientConfig {

    @Value("${zk.host}")
    private String zkHost;
    @Value("${zk.basePath}")
    private String zkBasePath;
    @Value("${zk.baseSleepTimeMs}")
    private Integer baseSleepTimeMs;
    @Value("${zk.maxRetries}")
    private Integer maxRetries;

    @Bean(value = "zkClient",initMethod = "init",destroyMethod = "destroy")
    public ZkClient zkClient(){
        ZkClient zkClient = new ZkClient(zkHost, zkBasePath);
        zkClient.setBaseSleepTimeMs(baseSleepTimeMs);
        zkClient.setMaxRetries(maxRetries);
        return zkClient;
    }

    //    @Bean(value = "curatorFramework",initMethod = "start",destroyMethod = "close")
//    public CuratorFramework curatorFramework(){
//        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
//        return CuratorFrameworkFactory.newClient("192.168.25.136:2181",retryPolicy);
//    }
}
