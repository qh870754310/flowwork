package com.qh.modules.sys.service;

import com.qh.modules.common.page.Page;
import com.qh.modules.sys.entity.OrganEntity;
import com.qh.modules.sys.entity.UserWindowDto;

import java.util.List;
import java.util.Map;

/**
 * 组织表
 *
 * Created by Administrator on 2018/4/26.
 */
public interface OrganService {

    /**
     * 分页查询组织审批选择范围
     *
     * @param userWindowDto
     * @param pageNum
     * @return
     */
    Page<UserWindowDto> queryPageByDto(UserWindowDto userWindowDto, int pageNum);

    List<OrganEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    OrganEntity queryObject(String id);

    /**
     * 根据code查询可能的组织
     *
     * @param code
     * @return
     */
    List<OrganEntity> queryListByCode(String code);

    String save(OrganEntity organ);

    int update(OrganEntity organ);

    int delete(String id);

    int deleteBatch(String[] ids);

    /**
     * 更新组织状态
     *
     * @param ids
     * @param type
     */
    void updateIsdel(String ids, String type);

    List<OrganEntity> queryListByBean(OrganEntity organEntity);
}
