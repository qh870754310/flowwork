package com.qh.modules.common.cache.ehcache;

/**
 * Created by Administrator on 2018/5/15.
 */
public interface ICache {

    CacheHelper getCacheHelper();

    boolean put(Object paramObject1, Object paramObject2);

    Object get(Object paramObject);

    <T> T get(Object paramObject, Class<T> paramClass);

    boolean exists(Object paramObject);

    boolean remove(Object paramObject);
}
