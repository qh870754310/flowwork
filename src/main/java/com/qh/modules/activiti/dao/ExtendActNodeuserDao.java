package com.qh.modules.activiti.dao;

import com.qh.modules.activiti.entity.ExtendActNodeuserEntity;
import com.qh.modules.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 节点可选人
 *
 * Created by Administrator on 2018/4/28.
 */
@Mapper
public interface ExtendActNodeuserDao extends BaseDao<ExtendActNodeuserEntity> {

    /**
     * 根据节点获取节点审批人员信息
     *
     * @param nodeId
     * @return
     */
    List<ExtendActNodeuserEntity> getNodeUserByNodeId(String nodeId);

    /**
     * 根据节点Id删除
     *
     * @param nodeId
     */
    void delByNodeId(String nodeId);
}
