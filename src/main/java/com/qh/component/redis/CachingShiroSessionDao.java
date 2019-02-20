package com.qh.component.redis;

import com.qh.modules.common.utils.RedisUtil;
import com.qh.modules.common.utils.SerializeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.io.Serializable;
import java.util.Collection;

/**
 * 对于分布式系统，一般都牵扯到Session共享问题，
 * 而想实现Session共享，就要实现Session的持久化操作，即是将内存中的Session持久化至缓存数据库。
 * 本篇就讲解一下使用Shiro实现Session会话的持久化操作，即SessionDAO的相关知识
 * SessionDAO是Shiro提供的一个数据交互层的interface接口，其作用是可以将Session写入到数据库中，然后可以对Session进行增删改查操作。
 *
 * 针对自定义的ShiroSession的Redis CRUD操作，通过isChanged标识符，确定是否需要调用Update方法
 * 通过配置securityManager在属性cacheManager查找从缓存中查找Session是否存在，如果找不到才调用下面方法
 * Shiro内部相应的组件（DefaultSecurityManager）会自动检测相应的对象（如Realm）是否实现了CacheManagerAware并自动注入相应的CacheManager。
 *
 * 如果自定义实现SessionDAO，继承CachingSessionDAO即可
 * 配置在sessionManager中，可选项，如果不修改默认使用MemorySessionDAO，即在本机内存中操作。
 * 如果想通过Redis管理Session，从这里入手。只需要实现类似DAO接口的CRUD即可。
 * 经过1：最开始通过继承AbstractSessionDAO实现，发现doReadSession方法调用过于频繁，所以改为通过集成CachingSessionDAO来实现。
 * 注意，本地缓存通过EhCache实现，失效时间一定要远小于Redis失效时间，这样本地失效后，会访问Redis读取，并重新设置Redis上会话数据的过期时间。
 *
 * 继承cachingsessionDao后会先检查缓存，缓存没有的再来jdbc中进行查找，这也是session共享的一种方案
 * 由于 Session 都持久化在 redis 中，导致 shiro 在请求处理中需要用到 session 的时候都要从 redis 中取数据并且反序列化，
 * 虽然 redis 的存取性能爆表，但是在如此场景中明显是 “铺张浪费”
 * 将从redis读取的session进行本地缓存，本地缓存失效时重新从redis读取并更新最后访问时间，解决shiro频繁读取redis问题
 *
 * 为实现Web应用的分布式集群部署，要解决登录session的统一。
 * 本文利用shiro做权限控制，redis做session存储，结合spring boot快速配置实现session共享。
 * CachingSessionDAO是一个抽象类，负责对Session进行缓存管理，所有Session都存入到一个缓存中;
 * Shiro提供自己的Cache和CacheManager两个接口。CacheManger负责管理Cache对象实例;
 * Session就是存放在activeSessions中
 *
 * Created by Administrator on 2018/4/19.
 */
@Component
public class CachingShiroSessionDao extends CachingSessionDAO {

    private static final Logger logger = LoggerFactory.getLogger(CachingShiroSessionDao.class);

    //保存到Redis的key的前缀 prefix+sessionId
    private String prefix = "";

    //设置会话的过期时间
    private int seconds = 0;

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 创建session，保存到数据库
     *
     * 如DefaultSessionManager在创建完session后会调用该方法；
     * 如保存到关系数据库/文件系统/NoSQL数据库；即可以实现会话的持久化；
     * 返回会话ID；主要此处返回的ID.equals(session.getId())
     *
     * @param session
     * @return
     */
    @Override
    protected Serializable doCreate(Session session) {
        //创建一个Id并设置给Session
        Serializable sessionId = this.generateSessionId(session);
        assignSessionId(session, sessionId);
        try {
            //session由Redis缓存失效决定，这里只是简单标识
            session.setTimeout(seconds);
            redisUtil.setObject(prefix + sessionId, session, seconds);
            logger.info("sessionId {} name {} 被创建", sessionId, session.getClass().getName());
        } catch (Exception e) {
            logger.warn("创建Session失败", e);
        }
        return sessionId;
    }

    /**
     * 重写CachingSessionDAO中readSession的方法，如果Session中没有登录信息就调用doReadSession方法从Redis中重读
     * 考虑到集群的时候退出登录无法删除集群机器本地session故而所有的都从 Redis 读取 Session.
     * @param sessionId
     * @return
     * @throws UnknownSessionException
     */
    @Override
    public Session readSession(Serializable sessionId) throws UnknownSessionException {
        Session session = super.getCachedSession(sessionId);
        // 如果缓存不存在或者缓存中没有登陆认证后记录的信息就重新从Redis中读取
        if (session == null || session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) == null) {
            session = this.doReadSession(sessionId);
            if (session == null) {
                throw new UnknownSessionException("There is no session with id [" + sessionId + "]");
            } else {
                //缓存
                // 重置Redis中缓存过期时间并缓存起来 只有设置change才能更改最后一次访问时间
                super.update(session);
                ((ShiroSession) session).setChanged(true);
                //      cache(session, session.getId());
            }
        }
        return session;
    }

    /**
     * 根据会话ID获取会话
     * 从Redis中读取Session,并重置过期时间
     * 获取session
     * 从 Redis 上读取 session，并缓存到本地 Cache
     *
     * @param sessionId 会话ID
     * @return
     */

    @Override
    protected Session doReadSession(Serializable sessionId) {
        Session session = null;
        try {
            String key = prefix + sessionId;
            session = (Session) redisUtil.getObject(key);
            if (session != null) {
                logger.info("sessionId {} name {} 被读取", sessionId, session.getClass().getName());
            }
        } catch (Exception e) {
            logger.warn("读取Session失败", e);
        }
        return session;
    }

    /*@Override
    protected Session doReadSession(Serializable sessionId) {
        Session session = null;
        Jedis jedis = null;
        try {
            jedis = redisUtil.getJedis();
            String key = prefix + sessionId;
            String value = jedis.get(key);
            if (StringUtils.isNotBlank(value)) {
                session = SerializeUtils.deserializeFromString(value);
                logger.info("sessionId {} ttl {}: ", sessionId, jedis.ttl(key));
                // 重置Redis中缓存过期时间
                jedis.expire(key, seconds);
                logger.info("sessionId {} name {} 被读取", sessionId, session.getClass().getName());
            }
        } catch (Exception e) {
            logger.warn("读取Session失败", e);
        } finally {
            redisUtil.releaseRedis(jedis);
        }
        return session;
    }*/

    public Session doReadSessionWithoutExpire(Serializable sessionId) {
        Session session = null;
        Jedis jedis = null;
        try {
            jedis = redisUtil.getJedis();
            String key = prefix + sessionId;
            String value = jedis.get(key);
            if (StringUtils.isNotBlank(value)) {
                session = SerializeUtils.deserializeFromString(value);
            }
        } catch (Exception e) {
            logger.warn("读取Session失败", e);
        } finally {
            redisUtil.releaseRedis(jedis);
        }
        return session;
    }

    /**
     * 更新会话：如更新会话最后访问时间/停止会话/设置超时时间/设置移除属性等会调用
     * 更新 session 到 Redis.
     *
     * @param session
     */
    @Override
    /*protected void doUpdate(Session session) {
        //如果设置会话过期/停止 没必要在更新了
        try {
            if (session instanceof ValidatingSession && !((ValidatingSession) session).isValid()) {
                return;
            }
        } catch (Exception e) {
            logger.error("ValidatingSession error");
        }
        try {
            try {
                redisUtil.setObject(prefix+session.getId(),session,seconds);
                logger.info("sessionId {} name {} 被更新", session.getId(), session.getClass().getName());
            } catch (Exception e) {
                logger.info("sessionId {} name {} 更新异常", session.getId(), session.getClass().getName());
                throw e;
            }
        } catch (Exception e) {
            logger.warn("更新Session失败", e);
        }
    }*/

    protected void doUpdate(Session session) {
        //如果会话过期/停止 没必要再更新了
        try {
            if (session instanceof ValidatingSession && !((ValidatingSession) session).isValid()) {
                return;
            }
        } catch (Exception e) {
            logger.error("ValidatingSession error");
        }
        Jedis jedis = null;
        try {
            if (session instanceof ShiroSession) {
                // 如果没有主要字段(除lastAccessTime以外其他字段)发生改变
                ShiroSession ss = (ShiroSession) session;
                if (!ss.isChanged()) {
                    return;
                }
                Transaction tx = null;
                try {
                    jedis = redisUtil.getJedis();
                    // 开启事务
                    tx = jedis.multi();
                    ss.setChanged(false);
                    tx.setex(prefix + session.getId(), seconds, SerializeUtils.serializeToString(ss));
                    logger.info("sessionId {} name {} 被更新", session.getId(), session.getClass().getName());
                    // 执行事务
                    tx.exec();
                } catch (Exception e) {
                    if (tx != null) {
                        // 取消执行事务
                        tx.discard();
                    }
                    throw e;
                }
            } else if (session instanceof Serializable) {
                jedis = redisUtil.getJedis();
                jedis.setex(prefix + session.getId(), seconds, SerializeUtils.serializeToString((Serializable) session));
                logger.info("sessionId {} name {} 作为非ShiroSession对象被更新, ", session.getId(), session.getClass().getName());
            } else {
                logger.warn("sessionId {} name {} 不能被序列化 更新失败", session.getId(), session.getClass().getName());
            }
        } catch (Exception e) {
            logger.warn("更新Session失败", e);
        } finally {
            redisUtil.releaseRedis(jedis);
        }
    }
    /**
     * 删除会话: 当会话过期/会话停止（如用户退出）会调有
     *
     * @param session
     */

    @Override
    protected void doDelete(Session session) {
        try {
            redisUtil.del(prefix + session.getId());
            logger.debug("Session {} 被删除", session.getId());
        } catch (Exception e) {
            logger.warn("删除Session失败", e);
        }
    }

    /**
     * 删除cache中缓存的Session
     *
     * @param sessionId
     */
    public void uncache(Serializable  sessionId) {
        Session session = this.readSession(sessionId);
        super.uncache(session);
        logger.info("取消session{}的缓存", sessionId);
    }

    /**
     * 返回本机Ehcache中Session
     */
    public Collection<Session> getEhCacheActiveSessions() {
        return super.getActiveSessions();
    }

    /**
     * 获取当前所有活跃用户，如果用户量多此方法影响性能
     */
    /*@Override
    public Collection<Session> getActiveSessions() {
        Jedis jedis = null;
        try {
            jedis = redisUtil.getJedis();
            Set<String> keys = jedis.keys(prefix + "*");
            if (CollectionUtils.isEmpty(keys)) {
                return null;
            }
            List<String> valueList = jedis.mget(keys.toArray(new String[0]));
            return SerializeUtils.deserializeFromStringController(valueList);
        } catch (Exception e) {
            logger.warn("统计Session信息失败", e);
        } finally {
            redisUtil.releaseRedis(jedis);
        }
        return null;
    }*/


    /*Shiro内嵌了如下SessionDAO实现：
                     SessionDAO
                     AbstractSessionDAO
    CachingSessionDAO              MemorySessionDAO
    EnterpriseCacheSessionDAO

    AbstractSessionDAO提供了SessionDAO的基础实现，如生成会话ID等；CachingSessionDAO提供了对开发者透明的会话缓存的功能，
    只需要设置相应的CacheManager即可；MemorySessionDAO直接在内存中进行会话维护；而EnterpriseCacheSessionDAO提供了
    缓存功能的会话维护，默认情况下使用MapCache实现，内部使用ConcurrentHashMap保存缓存的会话。
    Shiro提供了使用Ehcache进行会话存储，Ehcache可以配合TerraCotta实现容器无关的分布式集群。

    */
}
