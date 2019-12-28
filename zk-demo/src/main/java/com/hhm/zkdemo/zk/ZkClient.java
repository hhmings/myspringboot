package com.hhm.zkdemo.zk;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;

/**
 * @author houhaimin
 * @version 1.0
 * @date 2019/12/26 23:41
 */
@Slf4j
public class ZkClient {
    @Getter
    @Setter
    private String hostPort;
    @Getter
    @Setter
    private String basePath;
    @Getter
    @Setter
    private Integer baseSleepTimeMs;
    @Getter
    @Setter
    private Integer maxRetries;

    @Getter
    private CuratorFramework client;

    public ZkClient(String hostPort, String basePath) {
        this.hostPort = hostPort;
        this.basePath = basePath;
    }

    public <T> T lock(AbstractMutex<T> mutex) {
        String lockPath = new StringBuilder(this.basePath).append(mutex.getLockPath()).toString();
        boolean success = false;
        InterProcessMutex lock = new InterProcessMutex(this.client, lockPath);
        T t = null;
        try {
            try {
                success = lock.acquire(mutex.getTimeOut(), mutex.getTimeUnit());
                log.info("lock acquire succ. success :{}", success);
            } catch (Exception e1) {
                log.error("lock acquire failed. path : {}, error :{}", lockPath, e1);
            }
            if (success) {
                //业务逻辑部分
                t =  mutex.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (success) {
                    lock.release();
                    // 此处如果进行 /lock/myTest/1 节点的删除的话，可能会有并发问题，此处的节点不应该被删除
//                    log.error("aaa , isEmpty:{}, checkExists = {}",isEnpty,stat);
//                    if (this.client.checkExists().forPath(lockPath) != null &&
//                        this.client.getChildren().forPath(lockPath).isEmpty()){
//                        this.client.delete().forPath(lockPath);
//                    }
                }
            } catch (Exception e) {
                log.error("lock release failed. path :{} ,  error :{}",lockPath, e);
            }
        }
        return t;
    }

    public void init() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(this.baseSleepTimeMs, this.maxRetries);
        this.client = CuratorFrameworkFactory.newClient(hostPort, retryPolicy);
        this.client.start();
        if (this.client.checkExists().forPath(this.basePath) == null) {
            this.client
                    .create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                    .forPath(this.basePath);
        }
        log.info("zk started ...");
    }

    public void destroy() {
        try {
            if (this.client != null) {
                this.client.close();
            }
        } catch (Exception e) {
            log.error("zk close failed. error :{}", e);
        }
        log.info("zk close succ.");
    }
}
