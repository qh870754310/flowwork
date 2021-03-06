package com.qh.modules.app.interceptor;

import com.qh.modules.app.annotation.LoginRequired;
import com.qh.modules.app.service.ApiUserService;
import com.qh.modules.app.utils.JwtUtils;
import com.qh.modules.common.exception.MyException;
import com.qh.modules.sys.entity.UserEntity;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 类AuthorizationInterceptor的功能描述:
 * 权限(Token)验证
 * Token验证拦截器
 *
 * Created by Administrator on 2018/5/28.
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private ApiUserService apiUserService;

    public static final String CURRENT_USER = "userId";

    /**
     * 请求之前
     * @param request  请求对象
     * @param response 返回对象
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LoginRequired annotation;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(LoginRequired.class);
        } else {
            // 如果不是映射到方法直接通过
            return true;
        }
        //如果不需要登陆验证，直接通过
        if (annotation == null) {
            return true;
        }

        //需要验证，获取用户凭证
    //    String token = request.getHeader("x-access-token");
        String token = request.getHeader("Authorization");


        if (StringUtils.isBlank(token)) {
            token = request.getParameter(jwtUtils.getHeader());
        }

        //凭证为空
        if (StringUtils.isBlank(token)) {
            throw new MyException("无token，请重新登录");
        }

        //验证token
        Claims claims = jwtUtils.getClaimByToken(token);
        if (claims == null || jwtUtils.isTokenExpired(claims.getExpiration())) {
            throw new MyException("凭证失效，请重新登录");
        }

        //验证用户信息
        UserEntity user = apiUserService.queryObject(claims.getSubject());
        if (user == null) {
            throw new RuntimeException("用户不存在，请重新登录");
        }

        //设置userId到request里，后续根据userId，获取用户信息
        request.setAttribute(CURRENT_USER, claims.getSubject());
        return true;
    }
}
