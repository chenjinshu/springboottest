package com.cjs.springboottest.util;

import com.cjs.springboottest.bean.HttpResult;
import com.cjs.springboottest.bean.HttpResultCode;

/**
 * @author chen.jinshu (青禾)
 * 2019/07/08
 */
public class HttpResultUtil {

    public static <T> HttpResult<T> success(T object) {
        HttpResult<T> result = new HttpResult<T>();
        result.setCode(HttpResultCode.SUCCESS.getCode());
        result.setMsg(HttpResultCode.SUCCESS.getDescription());
        result.setData(object);
        return result;
    }

    public static <T> HttpResult<T> fail(Integer code, String msg) {
        HttpResult<T> result = new HttpResult<T>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }
}

