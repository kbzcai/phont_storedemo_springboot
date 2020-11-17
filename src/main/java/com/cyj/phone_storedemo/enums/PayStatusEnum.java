package com.cyj.phone_storedemo.enums;

import lombok.Getter;

@Getter
public enum  PayStatusEnum {
    UNPAID(0,"未支付"),
    PAID(1,"已支付");
    private Integer Code;
    private String msg;

    PayStatusEnum(Integer code, String msg) {
        Code = code;
        this.msg = msg;
    }
}
