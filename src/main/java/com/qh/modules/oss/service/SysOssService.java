package com.qh.modules.oss.service;

import com.qh.modules.oss.entity.SysOssEntity;

import java.util.List;
import java.util.Map;

/**
 * 类SysOssService的功能描述:
 * 文件上传
 *
 * Created by Administrator on 2018/5/14.
 */
public interface SysOssService {

    SysOssEntity queryObject(Long id);

    List<SysOssEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(SysOssEntity ossEntity);

    void update(SysOssEntity sysOssEntity);

    void delete(Long id);

    void deleteBatch(Long[] ids);
}
