package com.qh.modules.common.annotation;

import java.lang.annotation.*;

/**
 * 类的功能描述：
 * 数据权限注解类
 *
 * Created by Administrator on 2018/5/9.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataAuth {

    /**
     * 表的别名
     * @return
     */
    String tableAlias() default "";
}
