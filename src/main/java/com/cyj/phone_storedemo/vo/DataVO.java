package com.cyj.phone_storedemo.vo;

import lombok.Data;

import java.util.List;

@Data
public class DataVO {
    private List<PhoneCategoryVO> categoies;
    private List<PhoneInfoVO> phones;
}
