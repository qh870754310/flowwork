package com.qh.modules.app.service.impl;

import com.qh.modules.app.dao.ApiUserDao;
import com.qh.modules.app.entity.ApiUserEntity;
import com.qh.modules.app.service.ApiUserService;
import com.qh.modules.common.common.Constant;
import com.qh.modules.common.exception.MyException;
import com.qh.modules.common.utils.ShiroUtils;
import com.qh.modules.sys.dao.UserDao;
import com.qh.modules.sys.entity.UserEntity;
import com.qh.modules.sys.service.impl.UserServiceImpl;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/28.
 */
@Service("apiUserService")
public class ApiUserServiceImpl extends UserServiceImpl implements ApiUserService {

    @Autowired
    private ApiUserDao apiUserDao;

    @Autowired
    private UserDao userDao;

    @Override
    public ApiUserEntity userInfo(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new MyException("用户id不能为空");
        }
        return apiUserDao.userInfo(id);
    }

    @Override
    public int updatePassword(UserEntity newUser, ApiUserEntity oldUser) {
        if (newUser == null) {
            throw new MyException("用户信息不能为空!");
        }
        if (StringUtils.isEmpty(newUser.getNewPassWord())) {
            throw new MyException("新密码不能为空");
        }
        String newPassword = ShiroUtils.EncodeSalt(newUser.getPassWord(), oldUser.getSalt());
        if (Constant.SUPERR_USER.equals(oldUser.getId())) {
            throw new MyException("不能修改超级管理员密码!");
        }
        if (!newPassword.equals(oldUser.getPassWord())) {
            throw new MyException("密码不正确");
        }

        Map<String, Object> params = new HashMap<>();
        //生成salt
        String salt = RandomStringUtils.randomAlphanumeric(20);
        params.put("id", oldUser.getId());
        params.put("salt", salt);
        params.put("password", ShiroUtils.EncodeSalt(newUser.getPassWord(), salt));
        return userDao.updatePassword(params);
    }

    @Override
    public String login(UserEntity userEntity) {
        if(StringUtils.isEmpty(userEntity.getLoginName())) {
            throw new MyException("登录用户名不能为空");
        }
        if (StringUtils.isEmpty(userEntity.getPassWord())) {
            throw new MyException("登录密码不能为空");
        }
        UserEntity user = queryByLoginName(userEntity.getLoginName());
        if (user == null) {
            throw new MyException("登录用户名或密码错误");
        }
        //密码错误
        if(!user.getPassWord().equals(ShiroUtils.EncodeSalt(userEntity.getPassWord(), user.getSalt()))) {
            throw new MyException("登录用户名或密码错误");
        }
        return user.getId();
    }


}
