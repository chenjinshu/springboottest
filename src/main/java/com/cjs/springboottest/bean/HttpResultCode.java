package com.cjs.springboottest.bean;

/**
 * @author chen.jinshu (青禾)
 * 2019/07/08
 */
public enum HttpResultCode {

    /**
     * 请求成功
     */
    SUCCESS(0, "请求成功"),

    /**
     * 服务不可用
     */
    SERVICE_UNAVAILABLE(-1, "服务不可用"),

    /**
     * 未知错误
     */
    UNKNOWN_ERROR(-2, "未知错误"),

    /**
     * 参数错误
     */
    PARAM_ERROR(1000, "参数错误");

    private int code;

    private String description;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private HttpResultCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static HttpResultCode get(Long code) {
        if (code != null) {
            for (HttpResultCode element : HttpResultCode.values()) {
                if (element.getCode() == code) {
                    return element;
                }
            }
        }
        return null;
    }

}
