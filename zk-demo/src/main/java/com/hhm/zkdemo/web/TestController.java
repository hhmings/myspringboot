package com.hhm.zkdemo.web;

import com.hhm.zkdemo.zk.ZkClient;
import com.hhm.zkdemo.zk_mutex.TestZkMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author houhaimin
 * @version 1.0
 * @date 2019/12/28 22:06
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ZkClient zkClient;

    private int count = 0;


    @RequestMapping("/test1/{id}")
    public String test1(@PathVariable Integer id){
        myTest(id);
        return "ok";
    }

    public void myTest(Integer studentId){
        Integer lock = zkClient.lock(new TestZkMutex<Integer>(studentId) {
            @Override
            public Integer execute() {
                System.out.println("抢到锁了 ... studentId = " + studentId);
                count++;
                System.out.println("lock ... count = "+count+" , studentId = "+studentId);
                return studentId;
            }
        });
        System.out.println("finished ... count = "+count+" , studentId = "+studentId);
    }

}
