package com.qh.modules.common.aspect;

import com.qh.modules.common.annotation.DataAuth;
import com.qh.modules.common.common.Constant;
import com.qh.modules.common.exception.MyException;
import com.qh.modules.common.utils.StringUtils;
import com.qh.modules.common.utils.UserUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 类DataAuthAspect的功能描述：
 * 数据权限过滤 切面类
 * 实现AOP的切面主要有以下几个要素：
 * 1、使用@Aspect注解将一个java类定义为切面类
 * 2、使用@Pointcut定义一个切入点，可以是一个规则表达式，比如下例中某个package下的所有函数，也可以是一个注解等。
 * 3、根据需要在切入点不同位置的切入内容
 * 使用@Before在切入点开始处切入内容
 * 使用@After在切入点结尾处切入内容
 * 使用@AfterReturning在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
 * 使用@Around在切入点前后切入内容，并自己控制何时执行切入点自身的内容
 * 使用@AfterThrowing用来处理当切入内容部分抛出异常之后的处理逻辑
 *
 * Created by Administrator on 2018/5/9.
 */
@Aspect
@Component
public class DataAuthAspect {

    @Pointcut("@annotation(com.qh.modules.common.annotation.DataAuth)")
    public void dataAuthPointcut() {

    }

    @Before("dataAuthPointcut()")
    public void dataAuth(JoinPoint joinPoint) throws Throwable {
        //获取方面第一个参数
        Object params = joinPoint.getArgs()[0];
        //如果参数类型为Map类型
        if (params != null && params instanceof Map) {
            String currentUserId = UserUtils.getCurrentUserId();
            //如果当前用户不为超级管理员，则需要进行数据过滤
            if (!currentUserId.equals(Constant.SUPERR_USER)) {
                ((Map) params).put("dataAuthSql", dataAuthSql(joinPoint));
            }
        } else {
            throw new MyException("需要数据权限过滤，需要查询方法的第一个参数为Map类型，且不能为NULL");
        }
    }

    public String dataAuthSql(JoinPoint joinPoint) {
        //获取目标方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //通过方法签名获取数据过滤注解
        DataAuth annotation = signature.getMethod().getAnnotation(DataAuth.class);
        //通过注解获取别名
        String tableAlias = annotation.tableAlias();
        if(!StringUtils.isEmpty(tableAlias)) {
            tableAlias += ".";
        }
        StringBuilder dataAuthSql = new StringBuilder();
        dataAuthSql.append(" AND (");

        //获取用户授权部门
        String baids = UserUtils.getDataAuth(Constant.DataAuth.BA_DATA.getValue());
        //获取用户授权机构
        String bapids = UserUtils.getDataAuth(Constant.DataAuth.BAP_DATA.getValue());
        dataAuthSql.append(tableAlias);
        dataAuthSql.append("create_id = ");
        dataAuthSql.append("'" + UserUtils.getCurrentUserId() + "'");
        if (baids != null && !StringUtils.isEmpty(baids)) {
            dataAuthSql.append("OR ");
            dataAuthSql.append(tableAlias);
            dataAuthSql.append("baid IN (");
            dataAuthSql.append(baids);
            dataAuthSql.append(")");
        }
        if (bapids != null && !StringUtils.isEmpty(bapids)) {
            dataAuthSql.append("OR ");
            dataAuthSql.append(tableAlias);
            dataAuthSql.append("bapid IN (");
            dataAuthSql.append(bapids);
            dataAuthSql.append(")");
        }
        dataAuthSql.append(")");
        return dataAuthSql.toString();
    }
}
