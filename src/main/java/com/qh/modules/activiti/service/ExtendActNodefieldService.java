package com.qh.modules.activiti.service;

import com.qh.modules.activiti.entity.ExtendActNodefieldEntity;

import java.util.List;
import java.util.Map;

/**
 * 流程节点对应的字段权限表
 *
 * Created by Administrator on 2018/4/26.
 */
public interface ExtendActNodefieldService {

    List<ExtendActNodefieldEntity> queryList(Map<String, Object> map);

    /**
     * 根据节点集合查询
     *
     * @param nodes
     * @return
     */
    List<ExtendActNodefieldEntity> queryByNodes(List<String> nodes);
}
