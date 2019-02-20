package com.qh.modules.sh.dao;

import com.qh.modules.common.dao.BaseDao;
import com.qh.modules.sh.entity.ShStudentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author qh
 * @email 870754310@qq.com
 * @date 2018-07-20 13:22:00
 */
@Mapper
public interface ShStudentDao extends BaseDao<ShStudentEntity> {

    /**
     * 根据身份证查询学生信息
     * @param idCard
     * @return
     */
    ShStudentEntity queryObjectByCardNo(String idCard);

    Map queryObjectByOpenId(String openId);

    int addBind(Map map);

    ShStudentEntity getStudentInfoByCard(String idCard);

    /**
     * 根据类型和字典值查询id
     * @param mark
     * @param name
     * @return
     */
    @Select("select id from (select * from sys_code where mark like concat(#{mark},'_%') order by sort) T where  name = #{name}")
    String getIdByMarkAndName(@Param("mark") String mark, @Param("name") String name);

    @Select("select * from sh_student where id_card = #{idCard}")
    ShStudentEntity getLocalStudentInfoByCard(@Param("idCard") String idCard);

    List<ShStudentEntity> getStudentData();
}
