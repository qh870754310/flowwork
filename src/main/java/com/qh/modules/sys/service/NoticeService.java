package com.qh.modules.sys.service;

import com.qh.modules.common.page.Page;
import com.qh.modules.sys.entity.NoticeEntity;

import java.util.List;
import java.util.Map;

/**
 * 通知
 *
 * Created by Administrator on 2018/5/3.
 */
public interface NoticeService {

    NoticeEntity queryObject(String id);

    List<NoticeEntity> queryList(Map<String, Object> map);

    List<NoticeEntity> queryListByBean(NoticeEntity entity);

    int queryTotal(Map<String, Object> map);

    int save(NoticeEntity notice);

    int update(NoticeEntity notice);

    int delete(String id);

    int deleteBatch(String[] ids);

    /**
     * 分页列表
     * @param noticeEntity
     * @param pageNum
     * @return
     */
    Page<NoticeEntity> findPage(NoticeEntity noticeEntity, int pageNum);

    /**
     * 我的通知分页列表
     *
     * @param noticeEntity
     * @param pageNum
     * @return
     */
    Page<NoticeEntity> findMyNoticePage(NoticeEntity noticeEntity, int pageNum);

    /**
     * 我的通知条数
     *
     * @return
     */
    int MyNoticeCount();
}
