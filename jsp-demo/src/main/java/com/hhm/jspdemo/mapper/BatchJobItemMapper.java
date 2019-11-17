package com.hhm.jspdemo.mapper;

import com.hhm.jspdemo.domain.BatchJobItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author houhaimin
 * @version 1.0
 * @date 2019/11/17 17:25
 */
public interface BatchJobItemMapper {

    BatchJobItem selectByPrimaryKey(Long id);

    int batchInsert(List<BatchJobItem> records);

    int markBatchJobResult(
            @Param("id") Long batchJobItemId,
            @Param("hintMessage") String hintMessage,
            @Param("jobStatus") Integer jobStatus);

    List<BatchJobItem> selectBatchJobItemByBatchJobId(@Param("batchJobId") Long batchJobId);
}
