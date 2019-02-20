package com.qh.modules.sh.service.impl;

import com.qh.modules.common.utils.Utils;
import com.qh.modules.sh.dao.ShResourcesUploadDao;
import com.qh.modules.sh.entity.ShResourcesUploadEntity;
import com.qh.modules.sh.service.ShResourcesUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;



@Service("shResourcesUploadService")
public class ShResourcesUploadServiceImpl implements ShResourcesUploadService {
	@Autowired
	private ShResourcesUploadDao shResourcesUploadDao;
	
	@Override
	public ShResourcesUploadEntity queryObject(String id){
		return shResourcesUploadDao.queryObject(id);
	}
	
	@Override
	public List<ShResourcesUploadEntity> queryList(Map<String, Object> map){
		return shResourcesUploadDao.queryList(map);
	}

    @Override
    public List<ShResourcesUploadEntity> queryListByBean(ShResourcesUploadEntity entity) {
        return shResourcesUploadDao.queryListByBean(entity);
    }
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return shResourcesUploadDao.queryTotal(map);
	}
	
	@Override
	public int save(ShResourcesUploadEntity shResourcesUpload){
        shResourcesUpload.setId(Utils.uuid());
        shResourcesUpload.setCreateDate(new Date());
		return shResourcesUploadDao.save(shResourcesUpload);
	}
	
	@Override
	public int update(ShResourcesUploadEntity shResourcesUpload){
        return shResourcesUploadDao.update(shResourcesUpload);
	}
	
	@Override
	public int delete(String id){
        return shResourcesUploadDao.delete(id);
	}
	
	@Override
	public int deleteBatch(String[] ids){
        return shResourcesUploadDao.deleteBatch(ids);
	}

    @Override
    public List<ShResourcesUploadEntity> getImagesByType(String type) {
        return shResourcesUploadDao.getImagesByType(type);
    }

}
