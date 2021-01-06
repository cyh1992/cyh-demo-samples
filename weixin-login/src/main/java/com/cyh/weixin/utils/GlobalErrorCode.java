package com.cyh.weixin.utils;

/**
 * @author cyh
 * @date 2021/1/6 11:08
 */
public enum  GlobalErrorCode {

    SUCESS(200, "Success"),
    REDIRECT(302, "redirect"),
    UNKNOWN(-1, "Unknown error");

    private int errorCode;
    private String error;

    private GlobalErrorCode(int errorCode, String error) {
        this.errorCode = errorCode;
        this.error = error;
    }

    public static GlobalErrorCode valueOf(int code) {
        GlobalErrorCode ec = values.get(code);
        if (ec != null)
            return ec;
        return UNKNOWN;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getError() {
        return error;
    }

    public String render() {
        return errorCode + ":" + error;
    }

}
