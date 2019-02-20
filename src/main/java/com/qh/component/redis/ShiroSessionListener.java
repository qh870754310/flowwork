package com.qh.component.redis;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 类ShiroSessionListener的功能描述:
 * 如果只想监听某一个事件，可以继承SessionListenerAdapter实现
 * 会话监听器用于监听会话创建、过期及停止事件：
 * 发现用户登出后，Session没有从Redis中销毁，虽然当前重新new了一个，但会对统计带来干扰，通过SessionListener解决这个问题
 *
 * Created by Administrator on 2018/5/2.
 */
@Component
public class ShiroSessionListener extends SessionListenerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(ShiroSessionListener.class);

    @Autowired
    private CachingShiroSessionDao sessionDao;

    @Override
    public void onStart(Session session) {
        // 会话创建时触发
        logger.info("ShiroSessionListener session {} 被创建", session.getId());
    }

    @Override
    public void onStop(Session session) {
        sessionDao.delete(session);
        // 会话被停止时触发
        logger.info("ShiroSessionListener session {} 被销毁", session.getId());
    }

    @Override
    public void onExpiration(Session session) {
        sessionDao.delete(session);
        //会话过期时触发
        logger.info("ShiroSessionListener session {} 过期", session.getId());
    }
}
