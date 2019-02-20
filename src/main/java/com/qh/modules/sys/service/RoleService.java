package com.qh.modules.sys.service;

import com.qh.modules.common.page.Page;
import com.qh.modules.sys.entity.RoleEntity;
import com.qh.modules.sys.entity.UserWindowDto;

import java.util.List;
import java.util.Map;

/**
 * 角色表
 *
 * Created by Administrator on 2018/4/19.
 */
public interface RoleService {

    /**
     * 根据用户id查询用户所有的可用角色
     *
     * @param userId
     * @param status
     * @return
     */
    List<RoleEntity> queryByUserId(String userId, String status);

    /**
     * 分页查询角色审批选择范围
     *
     * @param userWindowDto
     * @param pageNum
     * @return
     */
    Page<UserWindowDto> queryPageByDto(UserWindowDto userWindowDto, int pageNum);

    List<RoleEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    RoleEntity queryObject(String id);

    /**
     * 根据角色id查询可用的组织机构
     *
     * @param roleId
     * @return
     */
    List<String> queryOrganRoleByRoleId(String roleId);

    void save(RoleEntity role) throws Exception;

    void update(RoleEntity role);

    void delete(String id);

    void deleteBatch(String[] ids) throws Exception;

    /**
     * 批量更新用户状态
     *
     * @param ids
     * @param status  状态(0正常 -1禁用)
     * @return
     */
    int updateBatchStatus(String[] ids, String status);
}
