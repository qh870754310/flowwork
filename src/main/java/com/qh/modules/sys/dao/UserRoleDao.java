package com.qh.modules.sys.dao;

import com.qh.modules.common.dao.BaseDao;
import com.qh.modules.sys.entity.UserRoleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户角色关系表
 *
 * Created by Administrator on 2018/5/11.
 */
@Mapper
public interface UserRoleDao extends BaseDao<UserRoleEntity> {

    /**
     * 根据用户list批量删除用户角色中间表
     *
     * @param users
     * @return
     */
    int deleteBatchByUserId(String[] users);

    /**
     * 根据角色list批量删除用户角色中间表
     *
     * @param roles
     * @return
     */
    int deleteBatchByRoleId(String[] roles);

    /**
     * 根据用户ID，获取角色ID列表
     *
     * @param userId
     * @return
     */
    List<String> queryRoleIdList(String userId);
}
