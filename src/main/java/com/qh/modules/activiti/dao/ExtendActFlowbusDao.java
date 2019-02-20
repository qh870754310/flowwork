package com.qh.modules.activiti.dao;

import com.qh.modules.activiti.entity.ExtendActFlowbusEntity;
import com.qh.modules.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * 业务流程关系表与activitiBaseEntity中字段一样
 *
 * Created by Administrator on 2018/5/2.
 */
@Mapper
public interface ExtendActFlowbusDao extends BaseDao<ExtendActFlowbusEntity> {

    /**
     * 根据业务id和流程实例id查询
     *
     * @param params key:instanceId 流程实例id key:busId 业务id
     * @return
     */
    ExtendActFlowbusEntity queryByBusIdInsId(Map<String, Object> params);

    /**
     * 根据业务id更新
     *
     * @param extendActFlowbusEntity
     * @return
     */
    int updateByBusId(ExtendActFlowbusEntity extendActFlowbusEntity);
}
