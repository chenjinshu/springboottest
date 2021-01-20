package com.cjs.springboottest.bean;

import lombok.Data;

/**
 * @author chen.jinshu (青禾)
 * 2019/07/08
 */
@Data
public class HttpResult<T> {

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体内容
     */
    private T data;
}
