package com.qh.modules.sys.controller;

import com.qh.modules.common.utils.PageUtils;
import com.qh.modules.common.utils.Query;
import com.qh.modules.common.utils.Result;
import com.qh.modules.sys.entity.SysLogEntity;
import com.qh.modules.sys.service.SysLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 类SysLogController的功能描述：
 * 系统日志
 *
 * Created by Administrator on 2018/5/11.
 */
@Controller
@RequestMapping("/sys/log")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("/list")
    @ResponseBody
    @RequiresPermissions("sys:log:list")
    public Result list(@RequestParam Map<String, Object> params) {
        //查询数据列表
        Query query = new Query(params);
        List<SysLogEntity> sysLogList = sysLogService.queryList(params);
        int total = sysLogService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(sysLogList, total, query.getLimit(), query.getPage());
        return Result.ok().put("page", pageUtil);
    }
}
