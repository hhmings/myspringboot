package com.hhm.zkdemo.zk;

import java.util.concurrent.TimeUnit;

/**
 * @author houhaimin
 * @version 1.0
 * @date 2019/12/28 21:04
 */
public abstract class AbstractMutex<T> implements Mutex<T> {
    private static final int DEFAULT_TIME_OUT = 5;
    private Integer timeOut;

    public abstract String getLockPath();

    public TimeUnit getTimeUnit() {
        return TimeUnit.SECONDS;
    }

    public Integer getTimeOut() {
        return timeOut == null ? DEFAULT_TIME_OUT : timeOut;
    }

    public void setTimeOut(Integer timeOut) {
        this.timeOut = timeOut;
    }
}
