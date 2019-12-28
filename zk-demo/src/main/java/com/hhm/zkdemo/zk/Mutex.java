package com.hhm.zkdemo.zk;

/**
 * @author houhaimin
 * @version 1.0
 * @date 2019/12/28 21:07
 */
public interface Mutex<T> {
    T execute();
}
