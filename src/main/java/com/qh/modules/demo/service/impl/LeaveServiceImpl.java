package com.qh.modules.demo.service.impl;

import com.qh.modules.common.common.Constant;
import com.qh.modules.common.exception.MyException;
import com.qh.modules.common.page.Page;
import com.qh.modules.common.page.PageHelper;
import com.qh.modules.common.utils.StringUtils;
import com.qh.modules.common.utils.UserUtils;
import com.qh.modules.common.utils.Utils;
import com.qh.modules.demo.dao.LeaveDao;
import com.qh.modules.demo.entity.LeaveEntity;
import com.qh.modules.demo.service.LeaveService;
import com.qh.modules.sys.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/13.
 */
@Service("leaveService")
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveDao leaveDao;


    @Override
    public Page<LeaveEntity> findPage(LeaveEntity leaveEntity, int pageNum) {
        PageHelper.startPage(pageNum, Constant.pageSize);
        leaveDao.queryList(leaveEntity);
        return PageHelper.endPage();
    }

    @Override
    public LeaveEntity queryObject(String id) {
        if (StringUtils.isEmpty(id)) {
            new MyException("id不为空!");
        }
        return leaveDao.queryObject(id);
    }

    @Override
    public List<LeaveEntity> queryList(Map<String, Object> map) {
        return leaveDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return leaveDao.queryTotal(map);
    }

    @Override
    public void save(LeaveEntity leaveEntity) {
        UserEntity currentUser = UserUtils.getCurrentUser();
        leaveEntity.setCode(Utils.getCode("D"));
        leaveEntity.setStatus(Constant.ActStauts.DRAFT.getValue());
        leaveEntity.setCreateId(currentUser.getId());
        leaveEntity.setCreateTime(new Date());
        leaveEntity.setId(Utils.uuid());
        leaveEntity.setStatus(Constant.ActStauts.DRAFT.getValue());
        leaveEntity.setUserId(UserUtils.getCurrentUserId());
        leaveEntity.setBapid(currentUser.getBapid());
        leaveEntity.setBaid(currentUser.getBaid());//未改
        leaveDao.save(leaveEntity);
    }

    @Override
    public void update(LeaveEntity leaveEntity) {
        if(StringUtils.isEmpty(leaveEntity.getId())){
            throw new MyException("请假id不能为空");
        }
        leaveEntity.setUpdateId(UserUtils.getCurrentUserId());
        leaveEntity.setUpdateTime(new Date());
        leaveDao.update(leaveEntity);
    }

    @Override
    public int delete(String id) {
        if(StringUtils.isEmpty(id)){
            throw  new MyException("请假id不能为空");
        }
        return leaveDao.delete(id);
    }

    @Override
    public void deleteBatch(String[] ids) {
        leaveDao.deleteBatch(ids);
    }
}
