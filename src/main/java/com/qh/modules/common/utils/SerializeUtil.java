package com.qh.modules.common.utils;

import org.apache.log4j.Logger;
import org.apache.shiro.codec.Base64;

import java.io.*;

/**
 * redis缓存的对象必须序列化
 *
 * Created by Administrator on 2018/4/19.
 */
public class SerializeUtil {

    private static Logger logger = Logger.getLogger(SerializeUtil.class);

    /**
     * 对象序列化
     *
     * @param obj 待序列化的对象
     * @return
     */
    public static String serialize(Object obj) {
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            return Base64.encodeToString(bos.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("serialize session error", e);
        } finally {
            try {
                oos.close();
                bos.close();
            } catch (IOException e) {
                logger.error("序列化字符串异常", e);
            }
        }
    }

    /**
     * 反序列化
     *
     * @param str 待序列化的字符串
     * @return
     */
    public static Object deserialize(String str) {
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            if (StringUtils.isEmpty(str)) {
                return null;
            }
            bis = new ByteArrayInputStream(Base64.decode(str));
            ois = new ObjectInputStream(bis);
            return ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException("deserialize session error", e);
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                logger.error("反序列化字符串异常", e);
            }
        }
    }
}
