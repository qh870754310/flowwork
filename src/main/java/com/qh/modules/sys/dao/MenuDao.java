package com.qh.modules.sys.dao;

import com.qh.modules.common.dao.BaseDao;
import com.qh.modules.sys.entity.MenuEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 菜单表
 *
 * Created by Administrator on 2018/4/19.
 */
@Mapper
public interface MenuDao extends BaseDao<MenuEntity> {

    /**
     * 根据登陆用户Id,查询出所有授权菜单
     *
     * @param userId
     * @return
     */
    List<MenuEntity> queryByUserId(String userId);

    /**
     * 根据父菜单id查询菜单
     *
     * @param parentId
     * @return
     */
    List<MenuEntity> queryListParentId(String parentId);

    /**
     * 查询除所有不包括按钮的菜单
     *
     * @param types
     * @return
     */
    List<MenuEntity> queryNotButtonList(String[] types);
}
