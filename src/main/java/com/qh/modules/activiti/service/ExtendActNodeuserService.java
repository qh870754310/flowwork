package com.qh.modules.activiti.service;

import com.qh.modules.activiti.entity.ExtendActNodeuserEntity;

import java.util.List;

/**
 * 节点可选人
 *
 * Created by Administrator on 2018/4/28.
 */
public interface ExtendActNodeuserService {

    /**
     * 根据节点id获取节点审批人员信息
     *
     * @param nodeId
     * @return
     */
    List<ExtendActNodeuserEntity> getNodeUserByNodeId(String nodeId);
}
