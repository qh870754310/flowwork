package com.qh.modules.app.resolver;

import com.qh.modules.app.annotation.CurrentUser;
import com.qh.modules.app.entity.ApiUserEntity;
import com.qh.modules.app.interceptor.AuthorizationInterceptor;
import com.qh.modules.app.service.ApiUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 类LoginUserHandlerMethodArgumentResolver的功能描述:
 * 要想 @loginUser 起作用，需要编写一个配套解析器，做法是实现 spring 提供的 HandlerMethodArgumentResolver 接口。
 *
 * Created by Administrator on 2018/5/28.
 */
@Component
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private ApiUserService apiUserService;


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(ApiUserEntity.class) && parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container, NativeWebRequest nativeWebRequest, WebDataBinderFactory factory) throws Exception {
        //这一句是从 request 作用域中取出名为 userId 的属性
        Object object = nativeWebRequest.getAttribute(AuthorizationInterceptor.CURRENT_USER, RequestAttributes.SCOPE_REQUEST);
        if (object == null) {
            return null;
        }
        //获取用户信息
        ApiUserEntity user = apiUserService.userInfo((String) object);
        return user;
    }
}
