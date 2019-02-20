package com.qh.modules.common.utils;

import com.qh.modules.common.common.Constant;
import com.qh.modules.sys.entity.UserEntity;
import com.qh.modules.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 类的功能描述.
 * 用户工具类
 *
 * Created by Administrator on 2018/4/19.
 */
public class UserUtils {

    @Autowired
    private UserService userService;

    /**
     * 获取当前登录用户
     *
     * @return
     */
    public static UserEntity getCurrentUser() {
        UserEntity userEntity = ShiroUtils.getUserEntity();
        return userEntity;
    }

    /**
     * 获取当前登录用户id 待完善缓存
     *
     * @return
     */
    public static String getCurrentUserId() {
        UserEntity user = ShiroUtils.getUserEntity();
        return user.getId();
    }

    /**
     * 获取机构部门数据权限
     *
     * @param type 1=部门权限，2=机构权限，3=部门机构权限
     * @return
     */
    public static String getDataAuth(String type) {
        UserEntity user = UserUtils.getCurrentUser();
        if (user == null) {
            return null;
        }
        if (Constant.DataAuth.BA_DATA.getValue().equals(type)) {
            return user.getBaids();
        }
        if (Constant.DataAuth.BAP_DATA.getValue().equals(type)) {
            return user.getBapids();
        }
        if (Constant.DataAuth.ALL_DATA.getValue().equals(type)) {
            return user.getBaids() + user.getBapids();
        }
        return null;
    }
}
