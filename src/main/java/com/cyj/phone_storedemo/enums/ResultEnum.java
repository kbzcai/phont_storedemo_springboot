package com.cyj.phone_storedemo.enums;

import lombok.Getter;

@Getter
public enum  ResultEnum {
    PHONE_STOCK_ERROR(0,"手机库存不足");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
