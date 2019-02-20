package com.qh.modules.activiti.service.impl;

import com.qh.modules.activiti.dao.ExtendActTasklogDao;
import com.qh.modules.activiti.entity.ExtendActTasklogEntity;
import com.qh.modules.activiti.service.ExtendActTasklogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/2.
 */
@Service("extendActTasklogService")
public class ExtendActTasklogServiceImpl implements ExtendActTasklogService {

    @Autowired
    private ExtendActTasklogDao extendActTasklogDao;


    @Override
    public List<ExtendActTasklogEntity> queryList(Map<String, Object> map) {
        return extendActTasklogDao.queryList(map);
    }

    @Override
    public int updateByTaskIdOpinion(ExtendActTasklogEntity extendActTasklogEntity) {
        return extendActTasklogDao.updateByTaskIdOpinion(extendActTasklogEntity);
    }

    @Override
    public void save(ExtendActTasklogEntity extendActTasklogEntity) {
        extendActTasklogDao.save(extendActTasklogEntity);
    }

    @Override
    public int updateByTaskId(ExtendActTasklogEntity extendActTasklogEntity) {
        return extendActTasklogDao.updateByTaskId(extendActTasklogEntity);
    }
}
