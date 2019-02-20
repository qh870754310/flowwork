package com.qh.modules.sys.service;

import com.qh.modules.sys.entity.MenuEntity;

import java.util.List;
import java.util.Map;

/**
 * 菜单表
 *
 * Created by Administrator on 2018/4/19.
 */
public interface MenuService {

    MenuEntity queryObject(String id);

    List<MenuEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    String save(MenuEntity menu);

    void update(MenuEntity menu);

    void delete(String id);

    void deleteBatch(String[] ids);

    /**
     * 根据登录用户id，查询出所有授权菜单 按钮 登录授权
     * @param userId
     * @return
     */
    List<MenuEntity> queryByUserId(String userId);

    /**
     * 根据父菜单Id查询菜单
     *
     * @param parentId
     * @return
     */
    List<MenuEntity> queryListParentId(String parentId);

    /**
     * 获取用户菜单列表， 主页查询用户菜单
     * @param userId
     * @return
     */
    List<MenuEntity> queryListUser(String userId);


    /**
     * 查询所有不包括按钮的菜单
     * @return
     */
    List<MenuEntity> queryNotButtonList();
}
