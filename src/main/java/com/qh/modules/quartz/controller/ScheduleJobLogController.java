package com.qh.modules.quartz.controller;

import com.qh.modules.common.annotation.SysLog;
import com.qh.modules.common.utils.PageUtils;
import com.qh.modules.common.utils.Query;
import com.qh.modules.common.utils.Result;
import com.qh.modules.quartz.entity.ScheduleJobLogEntity;
import com.qh.modules.quartz.service.ScheduleJobLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 类ScheduleJobLogController的功能描述:
 * 定时任务日志
 *
 * Created by Administrator on 2018/5/12.
 */
@RestController
@RequestMapping("/sys/scheduleLog")
public class ScheduleJobLogController {

    @Autowired
    private ScheduleJobLogService scheduleJobLogService;

    /**
     * 定时任务日志列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:schedule:log")
    @SysLog("定时任务日志列表")
    public Result list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        List<ScheduleJobLogEntity> jobList = scheduleJobLogService.queryList(query);
        int total = scheduleJobLogService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(jobList, total, query.getLimit(), query.getPage());
        return Result.ok().put("page", pageUtil);
    }

    /**
     * 定时任务日志信息
     */
    @RequestMapping("/info/{logId}")
    @SysLog("定时任务日志信息")
    public Result info(@PathVariable("logId") Long logId){
        ScheduleJobLogEntity log = scheduleJobLogService.queryObject(logId);
        return Result.ok().put("log", log);
    }
}
