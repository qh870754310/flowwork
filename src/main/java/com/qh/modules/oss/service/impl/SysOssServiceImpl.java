package com.qh.modules.oss.service.impl;

import com.qh.modules.oss.dao.SysOssDao;
import com.qh.modules.oss.entity.SysOssEntity;
import com.qh.modules.oss.service.SysOssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/14.
 */
@Service("sysOssService")
public class SysOssServiceImpl implements SysOssService {

    @Autowired
    private SysOssDao sysOssDao;

    @Override
    public SysOssEntity queryObject(Long id) {
        return sysOssDao.queryObject(id);
    }

    @Override
    public List<SysOssEntity> queryList(Map<String, Object> map) {
        return sysOssDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysOssDao.queryTotal(map);
    }

    @Override
    public void save(SysOssEntity ossEntity) {
        sysOssDao.save(ossEntity);
    }

    @Override
    public void update(SysOssEntity sysOssEntity) {
        sysOssDao.update(sysOssEntity);
    }

    @Override
    public void delete(Long id) {
        sysOssDao.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        sysOssDao.deleteBatch(ids);
    }
}
