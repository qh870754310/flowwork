package com.qh.modules.common.controller;

import com.qh.modules.common.utils.UserUtils;
import com.qh.modules.sys.entity.UserEntity;
import org.apache.log4j.Logger;

/**
 * 类的功能描述.
 * 公共的控件器，可在类中实现一些共同的方法和属性 持续更新中
 *
 * Created by Administrator on 2018/4/19.
 */
public class BaseController {

    private Logger logger = Logger.getLogger(getClass());

    /**
     * 获取当前登录用户
     *
     * @return
     */
    public UserEntity getUser() {
        return UserUtils.getCurrentUser();
    }

    /**
     * 获取当前登录用户的Id
     * @return
     */
    public String getUserId() {
        UserEntity userEntity = getUser();
        if (null != userEntity && null != userEntity.getId()) {
            return userEntity.getId();
        }
        return "";
    }
}
