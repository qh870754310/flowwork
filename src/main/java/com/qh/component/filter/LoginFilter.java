package com.qh.component.filter;

import com.qh.modules.common.utils.WebUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 判断登录过滤器
 *
 * Created by Administrator on 2018/10/12.
 */
public class LoginFilter extends AccessControlFilter {

    /**
     * 是否允许访问，mappedValue就是[urls]配置中拦截器参数部分，如果允许访问返回true，否则false；
     *
     * @param servletRequest
     * @param servletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        System.out.println(request.getRequestURL());
        String basePath = request.getContextPath();

        if (!SecurityUtils.getSubject().isAuthenticated()) {
            //判断session里是否有用户信息
            if (request.getHeader("x-requested-with") != null
                    && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                //如果是ajax请求响应头会有，x-requested-with
                response.setHeader("session-status", "timeout");//在响应头设置session状态
                return true;
            }
        }

        //如果是ajax请求响应头会有，x-requested-with
        if(WebUtils.isAjax(request)){
            //通过返回TRUE，通过前台的统一AJAX接受头部设置的sessionstatus参数，判断是否跳转到登录页面
            response.setCharacterEncoding("UTF-8");
            //在响应头设置session状态
            response.setHeader("session-status", "timeout");
            return Boolean.TRUE;
        }
        //FALSE  Session失效，切实非AJAX请求，验证是否，调用onAccessDenied跳转到登录页面
        return false;
    }

    /**
     * 表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；如果返回false表示该拦截器实例已经处理了，将直接返回即可。
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //保存Request和Response 到登录后的链接
        //将当前请求保存起来并重定向到登录页面
        saveRequestAndRedirectToLogin(request, response);
        return false;
    }
}
