package com.qh.modules.sys.dao;

import com.qh.modules.common.dao.BaseDao;
import com.qh.modules.sys.entity.NoticeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 通知
 *
 * Created by Administrator on 2018/5/9.
 */
@Mapper
public interface NoticeDao extends BaseDao<NoticeEntity> {

    /**
     * 我的通知列表
     *
     * @param noticeEntity
     * @return
     */
    List<NoticeEntity> findMyNotice(NoticeEntity noticeEntity);
}
