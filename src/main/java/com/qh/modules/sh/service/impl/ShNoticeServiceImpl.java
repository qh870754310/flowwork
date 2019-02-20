package com.qh.modules.sh.service.impl;

import com.qh.modules.sh.dao.ShNoticeDao;
import com.qh.modules.sh.entity.ShNoticeEntity;
import com.qh.modules.sh.service.ShNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.qh.modules.common.utils.Utils;


@Service("shNoticeService")
public class ShNoticeServiceImpl implements ShNoticeService {
	@Autowired
	private ShNoticeDao shNoticeDao;
	
	@Override
	public ShNoticeEntity queryObject(String id){
		return shNoticeDao.queryObject(id);
	}
	
	@Override
	public List<ShNoticeEntity> queryList(Map<String, Object> map){
		return shNoticeDao.queryList(map);
	}

    @Override
    public List<ShNoticeEntity> queryListByBean(ShNoticeEntity entity) {
        return shNoticeDao.queryListByBean(entity);
    }
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return shNoticeDao.queryTotal(map);
	}
	
	@Override
	public int save(ShNoticeEntity shNotice){
        shNotice.setId(Utils.uuid());
        shNotice.setCreatedate(new Date());
		return shNoticeDao.save(shNotice);
	}
	
	@Override
	public int update(ShNoticeEntity shNotice){
        return shNoticeDao.update(shNotice);
	}
	
	@Override
	public int delete(String id){
        return shNoticeDao.delete(id);
	}
	
	@Override
	public int deleteBatch(String[] ids){
        return shNoticeDao.deleteBatch(ids);
	}
	
}
