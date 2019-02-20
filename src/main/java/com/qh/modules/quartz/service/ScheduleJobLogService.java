package com.qh.modules.quartz.service;

import com.qh.modules.quartz.entity.ScheduleJobLogEntity;

import java.util.List;
import java.util.Map;

/**
 * 类ScheduleJobLogService的功能描述:
 * 定时任务日志
 *
 * Created by Administrator on 2018/5/12.
 */
public interface ScheduleJobLogService {

    /**
     * 保存定时任务日志
     *
     * @param log
     */
    void save(ScheduleJobLogEntity log);

    /**
     * 查询定时任务日志列表
     *
     * @param map
     * @return
     */
    List<ScheduleJobLogEntity> queryList(Map<String, Object> map);

    /**
     * 查询总数
     *
     * @param map
     * @return
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 根据ID，查询定时任务日志
     *
     * @param logId
     * @return
     */
    ScheduleJobLogEntity queryObject(Long logId);
}
