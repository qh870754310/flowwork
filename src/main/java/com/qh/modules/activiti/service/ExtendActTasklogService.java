package com.qh.modules.activiti.service;

import com.qh.modules.activiti.entity.ExtendActTasklogEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/28.
 */
public interface ExtendActTasklogService {

    List<ExtendActTasklogEntity> queryList(Map<String, Object> map);

    /**
     * 转办任务时更新任务日志，有可能会存在多次转办，就会产生多条任务日志，所有这里用 taskId+appAction为空 作唯一
     *
     * @param extendActTasklogEntity
     * @return
     */
    int updateByTaskIdOpinion(ExtendActTasklogEntity extendActTasklogEntity);

    void save(ExtendActTasklogEntity extendActTasklogEntity);

    /**
     * 根据任务id 更改日志
     *
     * @param extendActTasklogEntity
     * @return
     */
    int updateByTaskId(ExtendActTasklogEntity extendActTasklogEntity);
}
