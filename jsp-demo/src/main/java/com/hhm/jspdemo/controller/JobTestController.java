package com.hhm.jspdemo.controller;

import com.hhm.jspdemo.domain.BatchJob;
import com.hhm.jspdemo.mapper.BatchJobMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author houhaimin
 * @version 1.0
 * @date 2019/11/17 17:32
 */
@Controller

@Slf4j
public class JobTestController {

    @Autowired
    private BatchJobMapper batchJobMapper;

    @RequestMapping("/query/job_list")
    @ResponseBody
    public String queryJob(){
        BatchJob batchJob = batchJobMapper.selectByPrimaryKey(1L);
        log.info("queryJob : {}",batchJob);
        return "ok";
    }

}
