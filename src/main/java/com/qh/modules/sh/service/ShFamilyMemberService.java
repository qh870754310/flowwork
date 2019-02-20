package com.qh.modules.sh.service;


import com.qh.modules.sh.entity.ShFamilyMemberEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author qh
 * @email 870754310@qq.com
 * @date 2018-07-25 09:21:11
 */
public interface ShFamilyMemberService {
	
	ShFamilyMemberEntity queryObject(String id);
	
	List<ShFamilyMemberEntity> queryList(Map<String, Object> map);

    List<ShFamilyMemberEntity> queryListByBean(ShFamilyMemberEntity entity);
	
	int queryTotal(Map<String, Object> map);
	
	int save(ShFamilyMemberEntity shFamilyMember);
	
	int update(ShFamilyMemberEntity shFamilyMember);
	
	int delete(String id);
	
	int deleteBatch(String[] ids);

    List<ShFamilyMemberEntity> getFamilyMemberByIdCard(String idCard);
}
