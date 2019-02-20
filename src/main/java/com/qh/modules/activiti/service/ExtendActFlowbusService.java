package com.qh.modules.activiti.service;

import com.qh.modules.activiti.entity.ExtendActFlowbusEntity;

/**
 * 业务流程关系表与activitiBaseEntity中字段一样
 *
 * Created by Administrator on 2018/4/28.
 */
public interface ExtendActFlowbusService {

    /**
     * 根据业务id和流程实例id查询
     *
     * @param instanceId  流程实例id
     * @param busId       业务id
     * @return
     */
    ExtendActFlowbusEntity queryByBusIdInsId(String instanceId, String busId);

    /**
     * 根据业务id更新
     *
     * @param extendActFlowbusEntity
     */
    int updateByBusId(ExtendActFlowbusEntity extendActFlowbusEntity);

    void save(ExtendActFlowbusEntity extendActFlowbusEntity);
}
