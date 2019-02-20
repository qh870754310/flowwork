package com.qh.modules.sys.service.impl;

import com.qh.modules.sys.dao.RoleMenuDao;
import com.qh.modules.sys.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/11.
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl implements RoleMenuService {

    @Autowired
    private RoleMenuDao roleMenuDao;

    @Override
    public List<String> queryListByRoleId(String id) {
        return roleMenuDao.queryListByRoleId(id);
    }

    @Override
    public void save(Map<String, Object> map) {
        roleMenuDao.save(map);
    }

    @Override
    public void delete(String roleId) {
        roleMenuDao.delete(roleId);
    }

    @Override
    public void deleteBatch(String[] roleIds) {
        roleMenuDao.deleteBatch(roleIds);
    }
}
