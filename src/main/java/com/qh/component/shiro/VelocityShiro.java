package com.qh.component.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

/**
 * 类的功能描述.
 * Shiro权限标签(Velocity版)
 *
 * Created by Administrator on 2018/5/2.
 */
@Component
public class VelocityShiro {

    /**
     * 是否有该权限
     *
     * @param permission 权限标示
     * @return true：是 false：否
     */
    public boolean hasPermission(String permission) {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null && subject.isPermitted(permission)) {
            return true;
        }
        return false;
    }
}
