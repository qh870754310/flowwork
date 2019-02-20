package com.qh.modules.common.cache;

import com.qh.modules.common.cache.ehcache.CacheHelper;
import com.qh.modules.common.common.Constant;

/**
 * 用户缓存
 *
 * Created by Administrator on 2018/5/9.
 */
public class UserCache {

    private static CacheHelper helper = null;

    private static CacheHelper getCacheHelper() {
        if (helper == null) {
            helper = CacheHelper.getCache(Constant.USER_CACHE);
        }
        return helper;
    }

    public static boolean exists(Object key) {
        return getCacheHelper().exists(key);
    }

    public static <T> T get(Object key) {
        return getCacheHelper().get(key);
    }

    public static boolean put(Object key, Object value ) {
        return getCacheHelper().put(key, value);
    }

    public static boolean remove(Object key) {
        return getCacheHelper().remove(key);
    }
}
