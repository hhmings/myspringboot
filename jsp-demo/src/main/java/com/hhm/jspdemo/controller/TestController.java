package com.hhm.jspdemo.controller;

import com.hhm.jspdemo.domain.TestData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author houhaimin
 * @version 1.0
 * @date 2019/11/17 17:43
 */
@Controller
public class TestController {

    @GetMapping("/test")
    @ResponseBody
    public TestData test(){
        TestData testData = new TestData();
        testData.setCode(200);
        testData.setMsg("请求成功");
        return testData;
    }

    @PostMapping("/test1")
    @ResponseBody
    public TestData test1(){
        TestData testData = new TestData();
        testData.setCode(200);
        testData.setMsg("请求成功,哈哈哈哈哈");
        return testData;
    }
}
