package com.hhm.jspdemo.mapper;

import com.hhm.jspdemo.domain.BatchJob;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author houhaimin
 * @version 1.0
 * @date 2019/11/17 17:18
 */
public interface BatchJobMapper {
    int insert(BatchJob record);

    BatchJob selectByPrimaryKey(Long id);

    List<BatchJob> selectByBatchType(@Param("batchType") byte batchType,
                                     @Param("startNum") int startNum, @Param("size") int size);

    int updateBatchJobResult(@Param("id") Long batchId,

                             @Param("batchStatus") Integer batchStatus);
}
