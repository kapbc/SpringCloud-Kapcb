package com.kapcb.ccc.constants.enmus;

/**
 * <a>Title: ResultCodeEnum </a>
 * <a>Author: Kapcb <a>
 * <a>Description: Result Code Enum <a>
 *
 * @author Kapcb
 * @version 1.0.0
 * @date 2021/6/12 23:24
 */
public enum ResultCodeEnum {

    ;
    private final int code;

    private final String msg;

    ResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
