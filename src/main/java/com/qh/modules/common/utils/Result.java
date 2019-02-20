package com.qh.modules.common.utils;

import com.qh.modules.common.common.Constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 类Result的功能描述:
 * 返回数据实体类
 *
 * Created by Administrator on 2018/4/19.
 */
public class Result extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    public Result() {
        put("code", 0);
    }

    public Result(String code, String msg) {
        put("code", code);
        put("msg", msg);
    }

    public static Result error() {
        return new Result(Constant.RESULT.CODE_NO.getValue(), Constant.RESULT.MSG_NO.getValue());
    }

    public static Result error(String msg) {
        return error(Constant.RESULT.CODE_NO.getValue(), msg);
    }

    public static Result error(String code, String msg) {
        Result result = new Result();
        result.put("code",code);
        result.put("msg", msg);
        return result;
    }

    public static Result ok(String msg) {
        Result r = new Result();
        r.put("msg", msg);
        return r;
    }

    public static Result ok(Map<String, Object> map) {
        Result r = new Result();
        r.putAll(map);
        return r;
    }

    public static Result ok() {
        return new Result(Constant.RESULT.CODE_YES.getValue(),Constant.RESULT.MSG_YES.getValue());
    }

    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
