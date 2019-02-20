package com.qh.modules.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统页面视图
 *
 * Created by Administrator on 2018/5/2.
 */
@Controller
public class SysPageController {

    @RequestMapping("{module}/{url}.html")
    public String page(@PathVariable("module") String module, @PathVariable("url") String url) {
        return "modules/" + module + "/" + url + ".html";
    }

    @RequestMapping("/")
    public String index() {
        return "login.html";
    }
}
