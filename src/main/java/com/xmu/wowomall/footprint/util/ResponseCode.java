package com.xmu.wowomall.footprint.util;

/**
 *
 * @author wowoto
 * @date 12/11/2019
 */
public enum ResponseCode {
    ADD_FAILD(741,"足迹添加失败")

    ;

    private final Integer code;
    private final String message;

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
