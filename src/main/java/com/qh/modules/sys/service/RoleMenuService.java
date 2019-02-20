package com.qh.modules.sys.service;

import java.util.List;
import java.util.Map;

/**
 * 权限角色表
 *
 * Created by Administrator on 2018/5/10.
 */
public interface RoleMenuService {

    List<String> queryListByRoleId(String id);

    void save(Map<String, Object> map);

    void delete(String roleId);

    void deleteBatch(String[] roleIds);
}
