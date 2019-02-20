package com.qh.modules.activiti.service;

import com.qh.modules.activiti.entity.ExtendActModelEntity;
import com.qh.modules.common.page.Page;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 流程模板扩展表
 *
 * Created by Administrator on 2018/4/24.
 */
public interface ExtendActModelService {

    /**
     * 分页列表
     *
     * @param riskMarEntity
     * @param pageNum
     * @return
     */
    Page<ExtendActModelEntity> findPage(ExtendActModelEntity riskMarEntity, int pageNum);

    /**
     * 根据id查询对象
     *
     * @param id 对象id
     * @return
     */
    ExtendActModelEntity queryObject(String id);

    List<ExtendActModelEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    /**
     * 保存对象
     *
     * @param extendActModel
     * @return
     * @throws Exception
     */
    String save(ExtendActModelEntity extendActModel) throws Exception;

    /**
     * 更新对象
     *
     * @param extendActModel
     * @return
     */
    int update(ExtendActModelEntity extendActModel);

    /**
     * 删除流程
     *
     * @param id
     * @return
     */
    int delete(String id);

    int deleteBatch(String[] ids);

    /**
     * 部署流程
     *
     * @param modelId 模型id
     */
    void deploy(String modelId) throws IOException;
}
