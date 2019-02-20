package com.qh.modules.sys.service;

import com.qh.modules.oss.entity.SysConfigEntity;

import java.util.List;
import java.util.Map;

/**
 * 类SysConfigService的功能描述:
 * 系统配置信息
 *
 * Created by Administrator on 2018/5/11.
 */
public interface SysConfigService {

    /**
     * 获取list列表
     *
     * @param map
     * @return
     */
    List<SysConfigEntity> queryList(Map<String, Object> map);

    /**
     * 获取总记录数
     *
     * @param map
     * @return
     */
    int queryTotal(Map<String, Object> map);

    SysConfigEntity queryObject(Long id);

    /**
     * 保存配置信息
     *
     * @param config
     */
    void save(SysConfigEntity config);

    /**
     * 更新配置信息
     *
     * @param config
     */
    void update(SysConfigEntity config);

    /**
     * 删除配置信息
     *
     * @param ids
     */
    void deleteBatch(Long[] ids);

    /**
     * 根据key，获取配置的value值
     *
     * @param key           key
     * @param defaultValue  缺省值
     */
    String getValue(String key, String defaultValue);


    /**
     * 根据key，获取value的Object对象
     * @param key    key
     * @param clazz  Object对象
     */
    <T> T getConfigObject(String key, Class<T> clazz);

    void updateValueByKey(String key, String value);
}
