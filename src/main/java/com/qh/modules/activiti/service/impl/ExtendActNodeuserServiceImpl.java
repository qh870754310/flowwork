package com.qh.modules.activiti.service.impl;

import com.qh.modules.activiti.dao.ExtendActNodeuserDao;
import com.qh.modules.activiti.entity.ExtendActNodeuserEntity;
import com.qh.modules.activiti.service.ExtendActNodeuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/4/28.
 */
@Service("extendActNodeuserService")
public class ExtendActNodeuserServiceImpl implements ExtendActNodeuserService {

    @Autowired
    private ExtendActNodeuserDao extendActNodeuserDao;

    /**
     * 根据节点id获取节点审批人信息
     *
     * @param nodeId
     * @return
     */
    @Override
    public List<ExtendActNodeuserEntity> getNodeUserByNodeId(String nodeId) {
        return extendActNodeuserDao.getNodeUserByNodeId(nodeId);
    }
}
