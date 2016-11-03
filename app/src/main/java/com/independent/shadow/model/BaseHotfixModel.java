package com.independent.shadow.model;

/**
 * Created by chuck on 16/11/2.
 */
public class BaseHotfixModel {
    /**
     * code : integer,接口返回的状态
     * msg : string,接口状态消息
     */
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
