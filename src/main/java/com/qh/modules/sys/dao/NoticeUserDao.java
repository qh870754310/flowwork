package com.qh.modules.sys.dao;

import com.qh.modules.common.dao.BaseDao;
import com.qh.modules.sys.entity.NoticeUserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Administrator on 2018/5/9.
 */
@Mapper
public interface NoticeUserDao extends BaseDao<NoticeUserEntity> {

    /**
     * 根据通知id和用户id 更新
     *
     * @param noticeUserEntity
     * @return
     */
    int updateByNoticeIdUserId(NoticeUserEntity noticeUserEntity);
}
