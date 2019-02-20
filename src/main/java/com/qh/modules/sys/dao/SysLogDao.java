package com.qh.modules.sys.dao;

import com.qh.modules.common.dao.BaseDao;
import com.qh.modules.sys.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 类SysLogDao的功能描述:
 * 系统日志
 *
 * Created by Administrator on 2018/5/2.
 */
@Mapper
public interface SysLogDao extends BaseDao<SysLogEntity> {
}
