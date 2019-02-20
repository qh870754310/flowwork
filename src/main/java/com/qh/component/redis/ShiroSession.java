package com.qh.component.redis;

import org.apache.shiro.session.mgt.SimpleSession;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 经过上面的开发已经可以使用的，但发现每次访问都会多次调用SessionDAO的doUpdate方法，来更新Redis上数据，
 * 后来发现更新的字段只有LastAccessTime（最后一次访问时间），由于会话失效是由Redis数据过期实现的，
 * 这个字段意义不大，为了减少对Redis的访问，降低网络压力，实现自己的Session，在SimpleSession上套一层，
 * 增加一个标识位，如果Session除lastAccessTime意外其它字段修改，就标识一下，只有标识为修改的才
 * 可以通过doUpdate访问Redis，否则直接返回。这也是上面SessionDao中doUpdate中逻辑判断的意义
 *
 * 由于SimpleSession lastAccessTime更改后也会调用SessionDao update方法，
 * 增加标识位，如果只是更新lastAccessTime SessionDao update方法直接返回
 *
 * Created by Administrator on 2018/8/2.
 */
public class ShiroSession extends SimpleSession implements Serializable {

    // 除lastAccessTime以外其他字段发生改变时为true
    private boolean isChanged;

    public ShiroSession() {
        super();
        this.setChanged(true);
    }

    public ShiroSession(String host) {
        super(host);
        this.setChanged(true);
    }


    @Override
    public void setId(Serializable id) {
        super.setId(id);
        this.setChanged(true);
    }

    @Override
    public void setStopTimestamp(Date stopTimestamp) {
        super.setStopTimestamp(stopTimestamp);
        this.setChanged(true);
    }

    @Override
    public void setExpired(boolean expired) {
        super.setExpired(expired);
        this.setChanged(true);
    }

    @Override
    public void setTimeout(long timeout) {
        super.setTimeout(timeout);
        this.setChanged(true);
    }

    @Override
    public void setHost(String host) {
        super.setHost(host);
        this.setChanged(true);
    }

    @Override
    public void setAttributes(Map<Object, Object> attributes) {
        super.setAttributes(attributes);
        this.setChanged(true);
    }

    @Override
    public void setAttribute(Object key, Object value) {
        super.setAttribute(key, value);
        this.setChanged(true);
    }

    @Override
    public Object removeAttribute(Object key) {
        this.setChanged(true);
        return super.removeAttribute(key);
    }

    //更新最后访问时间不更新redis
    @Override
    public void touch() {
        this.setChanged(false);
        super.touch();
    }

    /**
     * 停止
     */
    @Override
    public void stop() {
        super.stop();
        this.setChanged(true);
    }

    /**
     * 设置过期
     */
    @Override
    protected void expire() {
        this.stop();
        this.setExpired(true);
    }

    public boolean isChanged() {
        return isChanged;
    }

    public void setChanged(boolean isChanged) {
        this.isChanged = isChanged;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected boolean onEquals(SimpleSession ss) {
        return super.onEquals(ss);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
