package com.hhm.jspdemo.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author houhaimin
 * @version 1.0
 * @date 2019/11/17 17:16
 */
@Data
public class BatchJobItem {

    private Long id;

    private Long batchId;

    private Byte jobStatus;

    private String jobRequest;

    private String jobErrorHintMsg;

    private Date createTime;

    private Date lastUpdateTime;
}
