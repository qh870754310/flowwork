package com.qh.modules.sys.dao;

import com.qh.modules.common.dao.BaseDao;
import com.qh.modules.sys.entity.RoleEntity;
import com.qh.modules.sys.entity.UserWindowDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 角色表
 *
 * Created by Administrator on 2018/4/19.
 */
@Mapper
public interface RoleDao extends BaseDao<RoleEntity> {

    /**
     * 根据用户查询角色集合
     *
     * @param userId
     * @param status
     * @return
     */
    List<RoleEntity> queryByUserId(@Param("userId") String userId, @Param("status") String status);

    /**
     * 查询角色审批选择范围
     *
     * @param userWindowDto
     */
    List<UserWindowDto> queryPageByDto(UserWindowDto userWindowDto);

    /**
     *根据角色id删除角色和组织关系表
     *
     * @param roleId
     * @return
     */
    int delRoleOrganByRoleId(String roleId);

    /**
     * 批量更新角色状态
     *
     * @param params key: ids  角色ids
     * @return
     */
    int updateBatchStatus(Map<String, Object> params);

    /**
     * 批量保存组织机构与角色关系表
     * @param map
     * @return
     */
    int batchSaveRoleOrgan(Map map);

    /**
     * 根据角色id查询可用的组织机构
     *
     * @param map
     * @return
     */
    List<String> queryOrganRoleByRoleId(Map map);
}
