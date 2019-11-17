package com.hhm.jspdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String test(){
        return "ok";
    }
}
