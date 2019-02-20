package com.qh.modules.activiti.service.impl;

import com.qh.modules.activiti.dao.ExtendActFlowbusDao;
import com.qh.modules.activiti.entity.ExtendActFlowbusEntity;
import com.qh.modules.activiti.service.ExtendActFlowbusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/28.
 */
@Service("extendActFlowbusService")
public class ExtendActFlowbusServiceImpl implements ExtendActFlowbusService {

    @Autowired
    private ExtendActFlowbusDao extendActFlowbusDao;

    @Override
    public ExtendActFlowbusEntity queryByBusIdInsId(String instanceId, String busId) {
        Map<String, Object> params = new HashMap<>();
        params.put("instanceId", instanceId);
        params.put("busId", busId);
        return extendActFlowbusDao.queryByBusIdInsId(params);
    }

    @Override
    public int updateByBusId(ExtendActFlowbusEntity extendActFlowbusEntity) {
        return extendActFlowbusDao.updateByBusId(extendActFlowbusEntity);
    }

    @Override
    public void save(ExtendActFlowbusEntity extendActFlowbusEntity) {
        extendActFlowbusDao.save(extendActFlowbusEntity);
    }
}
