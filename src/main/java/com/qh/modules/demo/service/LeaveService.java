package com.qh.modules.demo.service;

import com.qh.modules.common.page.Page;
import com.qh.modules.demo.entity.LeaveEntity;

import java.util.List;
import java.util.Map;

/**
 * 请假流程测试
 *
 * Created by Administrator on 2018/5/13.
 */
public interface LeaveService {

    Page<LeaveEntity> findPage(LeaveEntity leaveEntity, int pageNum);

    LeaveEntity queryObject(String id);

    List<LeaveEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(LeaveEntity leaveEntity);

    void update(LeaveEntity leaveEntity);

    int delete(String id);

    void deleteBatch(String[] ids);
}
