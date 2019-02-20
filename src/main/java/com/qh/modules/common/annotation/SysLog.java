package com.qh.modules.common.annotation;

import java.lang.annotation.*;

/**
 * 类SysLog的功能描述:
 * 系统日志注解
 *
 * Created by Administrator on 2018/4/19.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    String value() default "";
}
