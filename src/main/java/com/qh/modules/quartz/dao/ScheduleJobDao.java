package com.qh.modules.quartz.dao;

import com.qh.modules.common.dao.BaseDao;
import com.qh.modules.quartz.entity.ScheduleJobEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * 类ScheduleJobDao的功能描述:
 * 定时任务
 *
 * Created by Administrator on 2018/5/12.
 */
@Mapper
public interface ScheduleJobDao extends BaseDao<ScheduleJobEntity> {

    /**
     * 批量更新状态
     *
     * @param map
     * @return
     */
    int updateBatch(Map<String, Object> map);
}
