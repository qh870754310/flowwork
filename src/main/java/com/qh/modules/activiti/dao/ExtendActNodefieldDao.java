package com.qh.modules.activiti.dao;

import com.qh.modules.activiti.entity.ExtendActNodefieldEntity;
import com.qh.modules.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 流程节点对应的条件
 *
 * Created by Administrator on 2018/4/28.
 */
@Mapper
public interface ExtendActNodefieldDao extends BaseDao<ExtendActNodefieldEntity> {

    /**
     * 根据节点id删除
     *
     * @param nodeId
     */
    void delByNodeId(String nodeId);

    /**
     * 根据节点集合查询
     *
     * @param nodes
     * @return
     */
    List<ExtendActNodefieldEntity> queryByNodes(List<String> nodes);
}
