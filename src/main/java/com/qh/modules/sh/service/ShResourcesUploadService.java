package com.qh.modules.sh.service;

import com.qh.modules.sh.entity.ShResourcesUploadEntity;

import java.util.List;
import java.util.Map;

/**
 * 文件管理
 * 
 * @author qh
 * @email 870754310@qq.com
 * @date 2018-07-03 09:58:17
 */
public interface ShResourcesUploadService {
	
	ShResourcesUploadEntity queryObject(String id);
	
	List<ShResourcesUploadEntity> queryList(Map<String, Object> map);

    List<ShResourcesUploadEntity> queryListByBean(ShResourcesUploadEntity entity);
	
	int queryTotal(Map<String, Object> map);
	
	int save(ShResourcesUploadEntity shResourcesUpload);
	
	int update(ShResourcesUploadEntity shResourcesUpload);
	
	int delete(String id);
	
	int deleteBatch(String[] ids);

    List<ShResourcesUploadEntity> getImagesByType(String type);
}
