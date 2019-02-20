package com.qh.modules.quartz.service;

import com.qh.modules.quartz.entity.ScheduleJobEntity;

import java.util.List;
import java.util.Map;

/**
 * 类ScheduleJobService的功能描述:
 * 定时任务
 *
 * Created by Administrator on 2018/5/12.
 */
public interface ScheduleJobService {

    /**
     * 查询定时任务列表
     *
     * @param map
     * @return
     */
    List<ScheduleJobEntity> queryList(Map<String, Object> map);

    /**
     * 查询总数
     *
     * @param map
     * @return
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 根据ID，查询定时任务
     *
     * @param jobId
     * @return
     */
    ScheduleJobEntity queryObject(Long jobId);

    /**
     * 保存定时任务
     *
     * @param scheduleJob
     */
    void save(ScheduleJobEntity scheduleJob);

    /**
     * 更新定时任务
     *
     * @param scheduleJob
     */
    void update(ScheduleJobEntity scheduleJob);

    /**
     * 批量删除定时任务
     *
     * @param jobIds
     */
    void deleteBatch(Long[] jobIds);

    /**
     * 批量更新定时任务状态
     */
    int updateBatch(Long[] jobIds, int status);

    /**
     * 立即执行
     *
     * @param jobIds
     */
    void run(Long[] jobIds);

    /**
     * 暂停运行
     *
     * @param jobIds
     */
    void pause(Long[] jobIds);

    /**
     * 恢复运行
     *
     * @param jobIds
     */
    void resume(Long[] jobIds);
}
