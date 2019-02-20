package com.qh.modules.sys.service.impl;

import com.qh.modules.common.common.Constant;
import com.qh.modules.common.page.Page;
import com.qh.modules.common.page.PageHelper;
import com.qh.modules.common.utils.UserUtils;
import com.qh.modules.sys.dao.NoticeDao;
import com.qh.modules.sys.dao.NoticeUserDao;
import com.qh.modules.sys.entity.NoticeEntity;
import com.qh.modules.sys.entity.NoticeUserEntity;
import com.qh.modules.sys.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/7.
 */
@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeDao noticeDao;

    @Autowired
    private NoticeUserDao noticeUserDao;

    @Override
    public Page<NoticeEntity> findPage(NoticeEntity noticeEntity, int pageNum) {
        PageHelper.startPage(pageNum, Constant.pageSize);
        //超级管理员可查看所有通知
        if (!Constant.SUPERR_USER.equals(UserUtils.getCurrentUserId())) {
            noticeEntity.setUserId(UserUtils.getCurrentUserId());
        }
        noticeDao.queryListByBean(noticeEntity);
        return PageHelper.endPage();
    }

    @Override
    public Page<NoticeEntity> findMyNoticePage(NoticeEntity noticeEntity, int pageNum) {
        PageHelper.startPage(pageNum, Constant.pageSize);
        //超级管理员可以查看所有通知
        if (!Constant.SUPERR_USER.equals(UserUtils.getCurrentUserId())) {
            noticeEntity.setUserId(UserUtils.getCurrentUserId());
        }
        noticeDao.findMyNotice(noticeEntity);
        return PageHelper.endPage();
    }

    @Override
    public NoticeEntity queryObject(String id) {
        NoticeEntity noticeEntity = noticeDao.queryObject(id);
        //如果当前查阅人，是被通知的被通知人，那么就更改查询状态为已阅
        if (noticeEntity != null && UserUtils.getCurrentUserId().equals(noticeEntity.getUserId())) {
            NoticeUserEntity noticeUserEntity = new NoticeUserEntity();
            noticeUserEntity.setUserId(noticeEntity.getUserId());
            noticeUserEntity.setNoticeId(noticeEntity.getId());
            noticeUserEntity.setStatus(Constant.YESNO.YES.getValue());
            noticeUserDao.updateByNoticeIdUserId(noticeUserEntity);
        }
        return noticeDao.queryObject(id);
    }

    @Override
    public List<NoticeEntity> queryList(Map<String, Object> map) {
        return noticeDao.queryList(map);
    }

    @Override
    public List<NoticeEntity> queryListByBean(NoticeEntity entity) {
        return noticeDao.queryListByBean(entity);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return noticeDao.queryTotal(map);
    }

    @Override
    public int save(NoticeEntity notice) {
        return noticeDao.save(notice);
    }

    @Override
    public int update(NoticeEntity notice) {
        return noticeDao.update(notice);
    }

    @Override
    public int delete(String id) {
        return noticeDao.delete(id);
    }

    @Override
    public int deleteBatch(String[] ids) {
        return noticeDao.deleteBatch(ids);
    }

    @Override
    public int MyNoticeCount() {
        NoticeEntity noticeEntity = new NoticeEntity();
        int count = 0;
        //超级管理员可以查看所有通知
        if (!Constant.SUPERR_USER.equals(UserUtils.getCurrentUserId())) {
            noticeEntity.setUserId(UserUtils.getCurrentUserId());
        }
        noticeEntity.setShowStatus(Constant.YESNO.NO.getValue());
        List<NoticeEntity> myNotice = noticeDao.findMyNotice(noticeEntity);
        if (myNotice != null) {
            count = myNotice.size();
        }
        return count;
    }
}
