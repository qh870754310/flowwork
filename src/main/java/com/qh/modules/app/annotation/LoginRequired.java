package com.qh.modules.app.annotation;

import java.lang.annotation.*;

/**
 * 类Login的功能描述:
 * app登录效验
 *
 * Created by Administrator on 2018/5/28.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginRequired {
}
