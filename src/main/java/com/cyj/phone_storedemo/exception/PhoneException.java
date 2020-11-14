package com.cyj.phone_storedemo.exception;

import com.cyj.phone_storedemo.enums.ResultEnum;

public class PhoneException extends RuntimeException {
    public PhoneException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
    }
}
