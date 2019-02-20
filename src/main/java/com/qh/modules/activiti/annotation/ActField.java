package com.qh.modules.activiti.annotation;

import java.lang.annotation.*;

/**
 * 类的功能描述.
 * 在实体类中对字段进行注解，用于流程表单权限和分支条件设置
 *
 * Created by Administrator on 2018/4/26.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ActField {

    /**
     * 显示名字
     */
    String name();

    /**
     * 是否用于分支条件判断
     *
     * @return
     */
    boolean isJudg() default false;

}
