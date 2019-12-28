package com.hhm.zkdemo.zk_mutex;

import com.hhm.zkdemo.zk.AbstractMutex;

/**
 * @author houhaimin
 * @version 1.0
 * @date 2019/12/28 21:11
 */
public abstract class TestZkMutex<T> extends AbstractMutex<T> {
    private static final String PATH = "/mytest/";
    private Integer id;

    public TestZkMutex(Integer id) {
        this.id = id;
    }

    @Override
    public String getLockPath() {
        return PATH+id;
    }

    public Integer getId() {
        return id;
    }
}
