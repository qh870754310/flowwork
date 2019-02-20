package com.qh.modules.sh.controller;

import com.qh.modules.common.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/7/16.
 */
@Controller
@RequestMapping("/api/wechat")
public class WechatController {

    /**
     * 绑定用户
     *
     * @param request  HttpServletRequest对象
     * @param response HttpServletResponse对象
     * @param callback 回调对象
     */
    @RequestMapping("/bind")
    public Result bindUser(HttpServletRequest request, HttpServletResponse response, String callback) {
        String openId = request.getParameter("openId");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String identity_card = request.getParameter("identity_card");

        return Result.ok();
    }

    /**
     * 查询绑定情况
     *
     * @param request  HttpServletRequest对象
     * @param response HttpServletResponse对象
     * @param callback 回调对象
     */
    @RequestMapping("/queryBind")
    public Result queryBind(HttpServletRequest request, HttpServletResponse response, String callback) {
        String openId = request.getParameter("openId");
        return Result.ok();
    }

    /**
     * 解绑用户
     *
     * @param request  HttpServletRequest对象
     * @param response HttpServletResponse对象
     * @param callback 回调对象
     */
    @RequestMapping("/unbind")
    public Result unbindUser(HttpServletRequest request, HttpServletResponse response, String callback) {
        String openId = request.getParameter("openId");
        return Result.ok();
    }
}
