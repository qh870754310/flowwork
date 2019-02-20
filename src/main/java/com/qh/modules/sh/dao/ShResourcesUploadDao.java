package com.qh.modules.sh.dao;


import com.qh.modules.common.dao.BaseDao;
import com.qh.modules.sh.entity.ShResourcesUploadEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 文件管理
 * 
 * @author qh
 * @email 870754310@qq.com
 * @date 2018-07-03 09:58:17
 */
@Mapper
public interface ShResourcesUploadDao extends BaseDao<ShResourcesUploadEntity> {

    List<ShResourcesUploadEntity> getImagesByType(String type);
}
