package com.qh.modules.sh.service;


import com.qh.modules.sh.entity.Guidevt;
import com.qh.modules.sh.entity.ShStudentEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author qh
 * @email 870754310@qq.com
 * @date 2018-07-20 13:22:00
 */
public interface ShStudentService {
	
	ShStudentEntity queryObject(String id);
	
	List<ShStudentEntity> queryList(Map<String, Object> map);

    List<ShStudentEntity> queryListByBean(ShStudentEntity entity);
	
	int queryTotal(Map<String, Object> map);
	
	int save(ShStudentEntity shStudent);
	
	int update(ShStudentEntity shStudent);
	
	int delete(String id);
	
	int deleteBatch(String[] ids);

    ShStudentEntity queryObjectByCardNo(String idCard);

	Map queryObjectByOpenId(String openId);

	void addBind(String id, String openId) throws Exception;

	ShStudentEntity getStudentInfoByCard(String idCard);

    ShStudentEntity findStudentByCardId(String idCard);

    Map<String,String> getTest(String idCard);

    ShStudentEntity getLocalStudentInfoByCard(String idCard);

	int saveLocal(ShStudentEntity shStudent, String uuid);

    List<ShStudentEntity> getStudentData();

    Guidevt getStatusInfoByCandidateNo(String candidateNo);
}
