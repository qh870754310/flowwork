package com.qh.modules.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 权限角色表
 *
 * Created by Administrator on 2018/5/11.
 */
@Mapper
public interface RoleMenuDao {

    int delete(Map<String, Object> map);

    int delete(Object id);

    int deleteBatch(Object[] ids);

    void save(Map<String, Object> map);

    List<String> queryListByRoleId(String roleId);
}
