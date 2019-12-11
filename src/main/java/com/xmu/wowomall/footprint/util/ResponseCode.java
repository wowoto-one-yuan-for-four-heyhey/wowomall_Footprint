package com.xmu.wowomall.footprint.util;

public enum ResponseCode {
    AUTH_INVALID_ACCOUNT(700, ""),

    ORDER_UNKNOWN(720, "订单不存在"),
    ORDER_INVALID(721, "订单异常"),
    ORDER_INVALID_OPERATION(725, "订单非法操作"),

    FOOTPRINT_INVALID_OPERATION(800,"足迹非法操作"),
    /**
     * 删除一条足迹但用户ID不对应
     * 添加一条足迹goodsId为空
     * 添加一条足迹但用户ID与VO中的userId不对应
     */


    FOOTPRINT_UNKNOWN(801,"足迹不存在")

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
