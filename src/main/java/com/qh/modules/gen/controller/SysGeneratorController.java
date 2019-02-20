package com.qh.modules.gen.controller;

import com.alibaba.fastjson.JSON;
import com.qh.modules.common.common.Constant;
import com.qh.modules.common.utils.PageUtils;
import com.qh.modules.common.utils.Query;
import com.qh.modules.common.utils.Result;
import com.qh.modules.common.xss.XssHttpServletRequestWrapper;
import com.qh.modules.gen.service.SysGeneratorService;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 类SysGeneratorController的功能描述：
 * 代码生成器
 *
 * Created by Administrator on 2018/5/12.
 */
@Controller
@RequestMapping("/sys/generator")
public class SysGeneratorController {

    @Autowired
    private SysGeneratorService sysGeneratorService;

    /**
     * 列表
     *
     * @param params
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    @RequiresPermissions("sys:generator:list")
    public Result list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<Map<String, Object>> list = sysGeneratorService.queryList(query);
        int total = sysGeneratorService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
        return Result.ok().put("page", pageUtil);
    }

    /**
     * 生成代码
     *
     * @param request
     * @param response
     */
    @RequestMapping("/code")
    @RequiresPermissions("sys:generator:code")
    public void code(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] tableNames = new String[]{};
        //获取表名，不进行xss过滤
        HttpServletRequest orgRequest = XssHttpServletRequestWrapper.getOrgRequest(request);
        String tables = orgRequest.getParameter("tables");
        tableNames = JSON.parseArray(tables).toArray(tableNames);
        byte[] data = sysGeneratorService.generatorCode(tableNames, Constant.genType.webDown.getValue());
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"qhFrame.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());
    }

}
