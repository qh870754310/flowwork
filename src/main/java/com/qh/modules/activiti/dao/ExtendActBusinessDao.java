package com.qh.modules.activiti.dao;

import com.qh.modules.activiti.entity.ExtendActBusinessEntity;
import com.qh.modules.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 业务流程  对应的 业务表
 *
 * Created by Administrator on 2018/4/26.
 */
@Mapper
public interface ExtendActBusinessDao extends BaseDao<ExtendActBusinessEntity> {

    /**
     * 只查询流程业务类，不查询根目录和回调
     *
     * @param type1
     * @param type2
     * @return
     */
    List<ExtendActBusinessEntity> queryBusTree(@Param("type1") String type1, @Param("type2") String type2);

    /**
     * 根据extend_act_model中的modelid查询对应业务
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
     * 根据流程key查询
     *
     * @param actKey
     * @return
     */
    ExtendActBusinessEntity queryByActKey(String actKey);
}
