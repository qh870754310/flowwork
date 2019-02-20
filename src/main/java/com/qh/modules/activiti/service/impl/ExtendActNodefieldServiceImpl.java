package com.qh.modules.activiti.service.impl;

import com.qh.modules.activiti.dao.ExtendActNodefieldDao;
import com.qh.modules.activiti.entity.ExtendActNodefieldEntity;
import com.qh.modules.activiti.service.ExtendActNodefieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 流程节点对应的字段权限表
 *
 * Created by Administrator on 2018/4/28.
 */
@Service("extendActNodefieldService")
public class ExtendActNodefieldServiceImpl implements ExtendActNodefieldService {

    @Autowired
    private ExtendActNodefieldDao extendActNodefieldDao;

    @Override
    public List<ExtendActNodefieldEntity> queryList(Map<String, Object> map) {
        return extendActNodefieldDao.queryList(map);
    }

    @Override
    public List<ExtendActNodefieldEntity> queryByNodes(List<String> nodes) {
        return extendActNodefieldDao.queryByNodes(nodes);
    }
}
