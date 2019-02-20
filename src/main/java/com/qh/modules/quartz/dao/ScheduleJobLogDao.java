package com.qh.modules.quartz.dao;

import com.qh.modules.common.dao.BaseDao;
import com.qh.modules.quartz.entity.ScheduleJobLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 类ScheduleJobLogDao的功能描述:
 * 定时任务日志
 *
 * Created by Administrator on 2018/5/12.
 */
@Mapper
public interface ScheduleJobLogDao extends BaseDao<ScheduleJobLogEntity> {

}
