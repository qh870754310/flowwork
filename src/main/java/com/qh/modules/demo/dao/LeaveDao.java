package com.qh.modules.demo.dao;

import com.qh.modules.common.dao.BaseDao;
import com.qh.modules.demo.entity.LeaveEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 请假流程测试
 *
 * Created by Administrator on 2018/5/13.
 */
@Mapper
public interface LeaveDao extends BaseDao<LeaveEntity> {
}
