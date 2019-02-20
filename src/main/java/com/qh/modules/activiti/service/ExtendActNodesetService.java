package com.qh.modules.activiti.service;

import com.qh.modules.activiti.entity.ExtendActNodesetEntity;

import java.io.IOException;

/**
 * 流程节点配置
 *
 * Created by Administrator on 2018/4/26.
 */
public interface ExtendActNodesetService {

    /**
     * 根据nodeId查询节点信息
     *
     * @param nodeId
     * @return
     */
    ExtendActNodesetEntity queryByNodeId(String nodeId);

    /**
     * 保存节点业务信息， 审批范围设置， 节点条件设置
     *
     * @param extendActNodesetEntity
     * @return
     */
    ExtendActNodesetEntity saveNode(ExtendActNodesetEntity extendActNodesetEntity) throws IOException;

    /**
     * 根据nodeId和模型id查询节点信息
     *
     * @param nodeId
     * @param modelId
     * @return
     */
    ExtendActNodesetEntity queryByNodeIdModelId(String nodeId, String modelId);
}
