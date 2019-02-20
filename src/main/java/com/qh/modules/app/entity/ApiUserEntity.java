package com.qh.modules.app.entity;

import com.qh.modules.sys.entity.UserEntity;

/**
 * 类的功能描述：
 * 用户信息接口信息
 * 在jwt中承载用户身份信息的数据段叫payload。这里需要建立一个类"ApiUserEntity"用来表示payload。
 *
 * Created by Administrator on 2018/5/28.
 */
public class ApiUserEntity extends UserEntity {

    /**
     * 我的待办条数
     */
    private int myUpcomingCount;

    /**
     * 我的消息条数
     */
    private int myNoticeCount;

    public int getMyUpcomingCount() {
        return myUpcomingCount;
    }

    public void setMyUpcomingCount(int myUpcomingCount) {
        this.myUpcomingCount = myUpcomingCount;
    }

    public int getMyNoticeCount() {
        return myNoticeCount;
    }

    public void setMyNoticeCount(int myNoticeCount) {
        this.myNoticeCount = myNoticeCount;
    }
}
