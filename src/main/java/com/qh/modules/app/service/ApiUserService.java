package com.qh.modules.app.service;

import com.qh.modules.app.entity.ApiUserEntity;
import com.qh.modules.sys.entity.UserEntity;
import com.qh.modules.sys.service.UserService;

/**
 * 类UserService的功能描述:
 * 用户
 *
 * Created by Administrator on 2018/5/28.
 */
public interface ApiUserService extends UserService {
    /**
     * 根据id获取用户相关信息
     *
     * @param object
     * @return
     */
    ApiUserEntity userInfo(String object);

    /**
     * 修改密码
     *
     * @param newUser
     * @param oldUser
     * @return
     */
    int updatePassword(UserEntity newUser, ApiUserEntity oldUser);

    /**
     * 用户登录
     *
     * @param userEntity
     * @return   返回用户ID
     */
    String login(UserEntity userEntity);
}
