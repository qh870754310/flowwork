package com.qh.modules.sh.service.impl;

import com.qh.modules.common.utils.Utils;
import com.qh.modules.sh.dao.ShFamilyMemberDao;
import com.qh.modules.sh.entity.ShFamilyMemberEntity;
import com.qh.modules.sh.service.ShFamilyMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service("shFamilyMemberService")
public class ShFamilyMemberServiceImpl implements ShFamilyMemberService {
	@Autowired
	private ShFamilyMemberDao shFamilyMemberDao;
	
	@Override
	public ShFamilyMemberEntity queryObject(String id){
		return shFamilyMemberDao.queryObject(id);
	}
	
	@Override
	public List<ShFamilyMemberEntity> queryList(Map<String, Object> map){
		return shFamilyMemberDao.queryList(map);
	}

    @Override
    public List<ShFamilyMemberEntity> queryListByBean(ShFamilyMemberEntity entity) {
        return shFamilyMemberDao.queryListByBean(entity);
    }
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return shFamilyMemberDao.queryTotal(map);
	}
	
	@Override
	public int save(ShFamilyMemberEntity shFamilyMember){
        shFamilyMember.setId(Utils.uuid());
		return shFamilyMemberDao.save(shFamilyMember);
	}
	
	@Override
	public int update(ShFamilyMemberEntity shFamilyMember){
        return shFamilyMemberDao.update(shFamilyMember);
	}
	
	@Override
	public int delete(String id){
        return shFamilyMemberDao.delete(id);
	}
	
	@Override
	public int deleteBatch(String[] ids){
        return shFamilyMemberDao.deleteBatch(ids);
	}

    @Override
    public List<ShFamilyMemberEntity> getFamilyMemberByIdCard(String idCard) {
        return shFamilyMemberDao.getFamilyMemberByIdCard(idCard);
    }

}
