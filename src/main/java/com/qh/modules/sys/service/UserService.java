package com.qh.modules.sys.service;

import com.qh.modules.common.page.Page;
import com.qh.modules.sys.entity.UserEntity;
import com.qh.modules.sys.entity.UserWindowDto;

import java.util.List;
import java.util.Map;

/**
 * 系统用户表
 *
 * Created by Administrator on 2018/4/19.
 */
public interface UserService {

    /**
     * 根据登录用户查询有效的用户
     *
     * @param userLoginName
     * @return
     */
    UserEntity queryByLoginName(String userLoginName);

    /**
     * 用户对应的机构id,数据权限控制
     *
     * @param userId
     * @return
     */
    List<String> queryBapidByUserIdArray(String userId);

    /**
     * 用户对应的部门id,数据权限控制
     *
     * @param userId
     * @return
     */
    List<String> queryBaidByUserIdArray(String userId);

    /**
     * 根据条件分页查询
     *
     * @param userWindowDto
     * @param pageNum
     * @return
     */
    Page<UserWindowDto> findPage(UserWindowDto userWindowDto, int pageNum);

    List<UserEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    UserEntity queryObject(String userId);

    void update(UserEntity user);

    void save(UserEntity user);

    void delete(String id);

    void deleteBatch(String[] ids);

    /**
     * 修改密码
     *
     * @param user
     * @return
     */
    int updatePassword(UserEntity user);

    /**
     * 批量更新角色状态
     *
     * @param ids
     * @param status 状态(0正常 -1禁用)
     * @return
     */
    int updateBatchStatus(String[] ids, String status);

    /**
     * 重置密码
     * @param ids
     * @return
     */
    int resetPassWord(String[] ids);

    /**
     * 用户对应的机构id,数据权限控制
     * @param userId
     * @return key:roleId 角色id value:角色所对应的机构集合
     */
    Map<String,List<String>> queryBapidByUserId(String userId);

    /**
     * 用户对应的部门id,数据权限控制
     * @param userId
     * @return key:roleId 角色id value:角色所对应的部门集合
     */
    Map<String,List<String>> queryBaidByUserId(String userId);


}
