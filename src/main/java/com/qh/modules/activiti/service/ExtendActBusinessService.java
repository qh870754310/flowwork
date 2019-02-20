package com.qh.modules.activiti.service;

import com.qh.modules.activiti.entity.ExtendActBusinessEntity;

import java.util.List;
import java.util.Map;

/**
 * 业务流程  对应的 业务表
 *
 * Created by Administrator on 2018/4/25.
 */
public interface ExtendActBusinessService {

    /**
     * 只查询流程业务类，不查询根目录和回调
     *
     * @return
     */
    List<ExtendActBusinessEntity> queryBusTree();

    /**
     * 根据extend_act_model中的modelid查询对应的业务
     *
     * @param modelId
     * @return
     */
    ExtendActBusinessEntity queryActBusByModelId(String modelId);

    /**
     * 根据业务id查询该业务的所有回调
     *
     * @param parentId
     * @return
     */
    List<Map<String,Object>> queryCalBackByPid(String parentId);

    /**
     * 根据实体类条件查询 业务树
     *
     * @param businessEntity
     * @return
     */
    List<ExtendActBusinessEntity> queryListByBean(ExtendActBusinessEntity businessEntity);

    /**
     * 保存和更新
     *
     * @param businessEntity
     * @return
     */
    int edit(ExtendActBusinessEntity businessEntity);

    ExtendActBusinessEntity queryObject(String id);

    int delete(String id);

    ExtendActBusinessEntity queryByActKey(String actKey);
}
