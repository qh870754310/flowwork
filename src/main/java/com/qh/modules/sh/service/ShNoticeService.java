package com.qh.modules.sh.service;


import com.qh.modules.sh.entity.ShNoticeEntity;

import java.util.List;
import java.util.Map;

/**
 * 上海科技学院通知公告表
 * 
 * @author qh
 * @email 870754310@qq.com
 * @date 2018-07-02 14:30:01
 */
public interface ShNoticeService {
	
	ShNoticeEntity queryObject(String id);
	
	List<ShNoticeEntity> queryList(Map<String, Object> map);

    List<ShNoticeEntity> queryListByBean(ShNoticeEntity entity);
	
	int queryTotal(Map<String, Object> map);
	
	int save(ShNoticeEntity shNotice);
	
	int update(ShNoticeEntity shNotice);
	
	int delete(String id);
	
	int deleteBatch(String[] ids);
}
