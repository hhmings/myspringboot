package com.hhm.jspdemo.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author houhaimin
 * @version 1.0
 * @date 2019/11/17 17:15
 */
@Data
public class BatchJob {
    private Long id;

    private Byte batchStatus;

    private Integer itemCount;

    private String fileName;

    private Integer succItemCount;

    private Integer failItemCount;

    private Date createTime;

    private Date lastUpdateTime;
}
