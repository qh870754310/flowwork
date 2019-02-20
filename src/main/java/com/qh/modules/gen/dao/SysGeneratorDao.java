package com.qh.modules.gen.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 类SysGeneratorDao的功能描述:
 * 代码生成器
 *
 * Created by Administrator on 2018/5/12.
 */
@Mapper
public interface SysGeneratorDao {

    List<Map<String,Object>> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    List<Map<String,String>> queryColumns(String tableName);

    Map<String,String> queryTable(String tableName);
}
