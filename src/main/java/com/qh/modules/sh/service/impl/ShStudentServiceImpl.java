package com.qh.modules.sh.service.impl;

import com.qh.modules.common.utils.DbUtil;
import com.qh.modules.common.utils.Utils;
import com.qh.modules.sh.dao.ShStudentDao;
import com.qh.modules.sh.entity.Guidevt;
import com.qh.modules.sh.entity.ShStudentEntity;
import com.qh.modules.sh.service.ShStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service("shStudentService")
public class ShStudentServiceImpl implements ShStudentService {
	@Autowired
	private ShStudentDao shStudentDao;
	
	@Override
	public ShStudentEntity queryObject(String id){
		return shStudentDao.queryObject(id);
	}
	
	@Override
	public List<ShStudentEntity> queryList(Map<String, Object> map){
		return shStudentDao.queryList(map);
	}

    @Override
    public List<ShStudentEntity> queryListByBean(ShStudentEntity entity) {
        return shStudentDao.queryListByBean(entity);
    }
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return shStudentDao.queryTotal(map);
	}
	
	@Override
	public int save(ShStudentEntity shStudent){
        shStudent.setId(Utils.uuid());
		return shStudentDao.save(shStudent);
	}
	
	@Override
	public int update(ShStudentEntity shStudent){
        return shStudentDao.update(shStudent);
	}
	
	@Override
	public int delete(String id){
        return shStudentDao.delete(id);
	}
	
	@Override
	public int deleteBatch(String[] ids){
        return shStudentDao.deleteBatch(ids);
	}

    @Override
    public ShStudentEntity queryObjectByCardNo(String idCard) {
        return shStudentDao.queryObjectByCardNo(idCard);
    }

    @Override
    public Map queryObjectByOpenId(String openId) {
        return shStudentDao.queryObjectByOpenId(openId);
    }

    @Override
    public void addBind(String id, String openId) throws Exception {
        Map map = new HashMap();
        map.put("uuid", UUID.randomUUID().toString());
        map.put("userId", id);
        map.put("openId", openId);
        if (shStudentDao.addBind(map) != 1) {
        	throw new Exception("用户绑定失败");
		}
    }

	@Override
	public ShStudentEntity getStudentInfoByCard(String idCard) {
		return shStudentDao.getStudentInfoByCard(idCard);
	}

    @Override
    public ShStudentEntity findStudentByCardId(String idCard) {
		String idByMarkAndName = shStudentDao.getIdByMarkAndName("sex", "女");
		System.out.println("idByMarkAndName======================" + idByMarkAndName);
		String sql = "select * from usr_w5erp.v_xsjbxx2018 where sfzjh = ?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;//定义存放查询结果的结果集
		ShStudentEntity shStudentEntity = null;
		try{
			System.out.println(sql);
			conn= DbUtil.getConnection();
			System.out.println(conn);
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,idCard);
			rs=pstmt.executeQuery();//运行查询操作
			if(rs.next()){
				shStudentEntity=new ShStudentEntity();
				shStudentEntity.setIdCard(rs.getString("sfzjh"));
				shStudentEntity.setStudentNo(rs.getString("xh"));
				shStudentEntity.setName(rs.getString("xm"));
				shStudentEntity.setSex(shStudentDao.getIdByMarkAndName("sex", rs.getString("xbmc")));
				shStudentEntity.setBirthday(rs.getDate("CSRQ"));
				shStudentEntity.setCertificateType(shStudentDao.getIdByMarkAndName("certificate_type", rs.getString("sfzjlxmc")));
				shStudentEntity.setCandidateNo(rs.getString("ksh"));
				shStudentEntity.setLocalInstitution(rs.getString("sydmc"));
				shStudentEntity.setHeight(rs.getDouble("sg"));
				shStudentEntity.setWeight(rs.getDouble("tz"));
				shStudentEntity.setNation(shStudentDao.getIdByMarkAndName("nation", rs.getString("mzmc")));
				shStudentEntity.setPoliticalStatus(shStudentDao.getIdByMarkAndName("policital_status", rs.getString("zzmmmc")));
				shStudentEntity.setDomicilePlace(rs.getString("hkszd"));
				shStudentEntity.setHomeAddress(rs.getString("jtdz"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			//按顺序进行关闭
			DbUtil.close(conn, pstmt, rs);
		}
		System.out.println(shStudentEntity);
        return shStudentEntity;
    }

	@Override
	public Map<String, String> getTest(String idCard) {
		String sql = "select * from usr_w5erp.v_xsjbxx2018 where sfzjh = ?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;//定义存放查询结果的结果集
		ShStudentEntity shStudentEntity = null;
		try{
			System.out.println(sql);
			conn= DbUtil.getConnection();
			System.out.println(conn);
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,idCard);
			rs=pstmt.executeQuery();//运行查询操作
			Map<String, String> resultMap = DbUtil.getResultMap(rs);
			return resultMap;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			//按顺序进行关闭
			DbUtil.close(conn, pstmt, rs);
		}
		return null;
	}

    @Override
    public ShStudentEntity getLocalStudentInfoByCard(String idCard) {
        return shStudentDao.getLocalStudentInfoByCard(idCard);
    }

	@Override
	public int saveLocal(ShStudentEntity shStudent, String uuid) {
		shStudent.setId(uuid);
		return shStudentDao.save(shStudent);
	}

    @Override
    public List<ShStudentEntity> getStudentData() {
		List<ShStudentEntity> studentInfo = shStudentDao.getStudentData();
        return studentInfo;
    }

    @Override
    public Guidevt getStatusInfoByCandidateNo(String candidateNo) {
		String sql = "select * from usr_w5erp.v_yx_hjbl where ID = ?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;//定义存放查询结果的结果集
		Guidevt guidevt = null;
		try{
			conn= DbUtil.getConnection();
			System.out.println(conn);
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,candidateNo);
			rs=pstmt.executeQuery();//运行查询操作
			if(rs.next()){
				guidevt = new Guidevt();
				guidevt.setId(rs.getString("ID"));
				guidevt.setBlzt(rs.getInt("BLZT"));
				guidevt.setHjbz(rs.getString("HJBZ"));
				guidevt.setHjid(rs.getInt("HJID"));
				guidevt.setPcid(rs.getString("PCID"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			//按顺序进行关闭
			DbUtil.close(conn, pstmt, rs);
		}
        return guidevt;
    }

}
