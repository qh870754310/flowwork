package com.qh.modules.sys.dao;

import com.qh.modules.common.dao.BaseDao;
import com.qh.modules.oss.entity.SysConfigEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 类SysConfigDao的功能描述:
 * 系统配置信息
 *
 * Created by Administrator on 2018/5/11.
 */
@Mapper
public interface SysConfigDao extends BaseDao<SysConfigEntity> {

    /**
     * 根据key，查询value
     */
    String queryByKey(String key);

    /**
     * 根据key，更新value
     *
     * @param key
     * @param value
     * @return
     */
    int updateValueByKey(@Param("key") String key, @Param("value") String value);
}
