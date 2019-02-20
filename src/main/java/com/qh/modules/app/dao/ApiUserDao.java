package com.qh.modules.app.dao;

import com.qh.modules.app.entity.ApiUserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户表
 *
 * Created by Administrator on 2018/5/28.
 */
@Mapper
public interface ApiUserDao {

    /**
     * 根据id获取用户相关信息
     *
     * @param id
     * @return
     */
    ApiUserEntity userInfo(String id);
}
