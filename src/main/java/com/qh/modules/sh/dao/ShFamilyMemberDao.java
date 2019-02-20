package com.qh.modules.sh.dao;

import com.qh.modules.common.dao.BaseDao;
import com.qh.modules.sh.entity.ShFamilyMemberEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author qh
 * @email 870754310@qq.com
 * @date 2018-07-25 09:21:11
 */
@Mapper
public interface ShFamilyMemberDao extends BaseDao<ShFamilyMemberEntity> {

    List<ShFamilyMemberEntity> getFamilyMemberByIdCard(String idCard);
}
