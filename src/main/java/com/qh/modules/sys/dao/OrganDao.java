package com.qh.modules.sys.dao;

import com.qh.modules.common.dao.BaseDao;
import com.qh.modules.sys.entity.OrganEntity;
import com.qh.modules.sys.entity.UserWindowDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2018/4/26.
 */
@Mapper
public interface OrganDao extends BaseDao<OrganEntity> {

    /**
     * 根据实体条件查询
     *
     * @param userWindowDto
     */
    List<UserWindowDto> queryPageByDto(UserWindowDto userWindowDto);
}
