package com.qh.modules.sys.service.impl;

import com.qh.modules.common.common.Constant;
import com.qh.modules.common.page.Page;
import com.qh.modules.common.page.PageHelper;
import com.qh.modules.common.utils.UserUtils;
import com.qh.modules.common.utils.Utils;
import com.qh.modules.sys.dao.RoleDao;
import com.qh.modules.sys.dao.RoleMenuDao;
import com.qh.modules.sys.dao.UserRoleDao;
import com.qh.modules.sys.entity.RoleEntity;
import com.qh.modules.sys.entity.UserEntity;
import com.qh.modules.sys.entity.UserWindowDto;
import com.qh.modules.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/19.
 */
@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RoleMenuDao roleMenuDao;

    @Autowired
    private UserRoleDao userRoleDao;


    @Override
    public List<RoleEntity> queryByUserId(String userId, String status) {
        return roleDao.queryByUserId(userId, status);
    }

    @Override
    public Page<UserWindowDto> queryPageByDto(UserWindowDto userWindowDto, int pageNum) {
        PageHelper.startPage(pageNum, Constant.pageSize);
        roleDao.queryPageByDto(userWindowDto);
        return PageHelper.endPage();
    }

    @Override
    public List<RoleEntity> queryList(Map<String, Object> map) {
        return roleDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return roleDao.queryTotal(map);
    }

    @Override
    public RoleEntity queryObject(String id) {
        return roleDao.queryObject(id);
    }

    @Override
    public List<String> queryOrganRoleByRoleId(String roleId) {
        Map params = new HashMap();
        params.put("roleId", roleId);
        params.put("isDel", Constant.YESNO.NO.getValue());
        return roleDao.queryOrganRoleByRoleId(params);
    }

    @Override
    @Transactional
    public void save(RoleEntity role) throws Exception {
        UserEntity currentUser = UserUtils.getCurrentUser();
        role.setCreateId(currentUser.getId());
        role.setCreateTime(new Date());
        role.setId(Utils.uuid());
        role.setBaid(currentUser.getBaid());
        role.setBapid(currentUser.getBapid());
        roleDao.save(role);
        saveRtable(role);
    }

    /**
     * 保存角色与菜单，角色与组织关系表
     *
     * @param role
     */
    public void saveRtable(RoleEntity role) {
        if (role.getMenuIdList() != null && role.getMenuIdList().size() > 0) {
            Map map = new HashMap();
            map.put("roleId", role.getId());
            map.put("menuIdList", role.getMenuIdList());
            roleMenuDao.save(map);
        }

        if (role.getOrganIdList() != null && role.getOrganIdList().size() > 0) {
            Map organ = new HashMap();
            organ.put("role_id", role.getId());
            organ.put("organIdList", role.getOrganIdList());
            roleDao.batchSaveRoleOrgan(organ);
        }

    }

    @Override
    @Transactional
    public void update(RoleEntity role) {
        role.setUpdateTime(new Date());
        roleDao.update(role);
        //先删除所有角色菜单关系，在批量保存
        roleMenuDao.delete(role.getId());
        //先删除所有角色组织关系，在批量保存
        roleDao.delRoleOrganByRoleId(role.getId());
        saveRtable(role);
    }

    @Override
    public void delete(String id) {
        roleDao.delete(id);
    }

    @Override
    @Transactional
    public void deleteBatch(String[] ids) throws Exception {
        roleDao.deleteBatch(ids);
        roleMenuDao.deleteBatch(ids);
        userRoleDao.deleteBatch(ids);
    }

    @Override
    public int updateBatchStatus(String[] ids, String status) {
        Map<String, Object> params = new HashMap<>();
        params.put("ids", ids);
        params.put("status", status);
        return roleDao.updateBatchStatus(params);
    }

}
