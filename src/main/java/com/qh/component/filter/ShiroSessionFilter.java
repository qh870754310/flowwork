package com.qh.component.filter;

import com.alibaba.druid.support.json.JSONUtils;
import com.qh.modules.common.utils.Result;
import com.qh.modules.sys.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.mgt.SessionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * 问题描述：
 * 当访问一个需要登录的页面时，会有过滤器或者拦截器进行过滤拦截，如果用户没有登录，则跳转到登录页面。
 * 当用户已经登录进入系统后，然后长时间没操作，等到session过期后，再点击一个ajax请求操作时，这时再跳转到登录页面就不合适了，因为这是ajax操作，拦截后跳到页面返回的结果js识别不了。
 *
 * 解决方法：
 * 在过滤器或者拦截器上做识别，针对页面跳转请求和ajax请求分别处理。
 * Created by Administrator on 2018/10/12.
 */
public class ShiroSessionFilter implements Filter {

    private Logger log = LoggerFactory.getLogger(ShiroSessionFilter.class);

    @Autowired
    SessionContext sessionContext;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse) response;

        //判断是否为ajax请求，默认不是
        boolean isAjaxRequest = false;
        if(!StringUtils.isBlank(req.getHeader("x-requested-with")) && req.getHeader("x-requested-with").equals("XMLHttpRequest")){
            isAjaxRequest = true;
        }

        UserEntity user = (UserEntity) sessionContext.get("userId");
        if (user != null && user.getId() != null) {
            filterChain.doFilter(request, response);
        } else { //Session用户为空，登录过期
            if(isAjaxRequest) {// 如果是ajax请求，则不是跳转页面，使用response返回结果
                res.setHeader("noAuthentication", "true");
                Result result = new Result();
                result.put("msg", "登录已失效，请刷新页面或重新登录！");
                res.setContentType("application/json;charset=UTF-8");
                PrintWriter writer = res.getWriter();
                writer.write(JSONUtils.toJSONString(result));
                writer.close();
                res.flushBuffer();
            } else {
                res.sendRedirect("/login.html");
            }
        }

    }

    @Override
    public void destroy() {

    }
}
