package com.qh.modules.gen.service.impl;

import com.qh.modules.gen.dao.SysGeneratorDao;
import com.qh.modules.gen.service.SysGeneratorService;
import com.qh.modules.gen.utils.GenUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * Created by Administrator on 2018/5/12.
 */
@Service
public class SysGeneratorServiceImpl implements SysGeneratorService {

    @Autowired
    private SysGeneratorDao sysGeneratorDao;

    @Override
    public List<Map<String, Object>> queryList(Map<String, Object> map) {
        return sysGeneratorDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysGeneratorDao.queryTotal(map);
    }

    @Override
    public byte[] generatorCode(String[] tableNames, int genType) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableName : tableNames) {
            //查询表信息
            Map<String, String> table = queryTable(tableName);
            //查询列信息
            List<Map<String, String>> columns = queryColumns(tableName);
            //取得根目录路径
            String rootPath = getClass().getResource("/").getFile().toString();
            //生成代码
            GenUtils.generatorCode(table, columns, zip, genType);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    @Override
    public Map<String,String> queryTable(String tableName) {
        return sysGeneratorDao.queryTable(tableName);
    }

    @Override
    public List<Map<String, String>> queryColumns(String tableName) {
        return sysGeneratorDao.queryColumns(tableName);
    }
}
