package com.qh.component.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import java.io.Serializable;

/**
 * 重写sessionManager的retrieveSession方法。首先从request中获取session,如果request中不存在再走原来的从redis中获取。
 * 这样可以让一个请求的多次访问redis问题得到解决，因为request的生命周期为浏览器发送一个请求到接收服务器的一次响应完成，
 * 因此，在一次请求中，request中的session是一直存在的，并且不用担心session超时过期等的问题。这样就可以达到有多少次请求就
 * 几乎有多少次访问redis,大大减少单次请求，频繁访问redis的问题。大大减少redis的并发数量。此实现方法最为简单。
 * Created by Administrator on 2018/8/31.
 */
public class ShiroSessionManager extends DefaultWebSessionManager {

    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        Serializable sessionId = getSessionId(sessionKey);
        ServletRequest request = null;
        if (sessionKey instanceof WebSessionKey) {
            request = ((WebSessionKey) sessionKey).getServletRequest();
        }

        if (request != null && null != sessionId) {
            Object sessionObj = request.getAttribute(sessionId.toString());
            if (sessionObj != null) {
                return (Session) sessionObj;
            }
        }
        Session session = super.retrieveSession(sessionKey);
        if (request != null && null != sessionId) {
            request.setAttribute(sessionId.toString(), session);
        }
        return session;
    }
}
