package com.qh.modules.common.validator;

import com.qh.modules.common.common.RRException;
import org.apache.commons.lang.StringUtils;

/**
 * 类Assert的功能描述:
 * 数据校验
 *
 * Created by Administrator on 2018/5/13.
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new RRException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new RRException(message);
        }
    }
}
