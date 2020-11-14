package com.cyj.phone_storedemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyj.phone_storedemo.entity.PhoneInfo;
import com.cyj.phone_storedemo.mapper.PhoneInfoMapper;
import com.cyj.phone_storedemo.service.PhoneService;
import com.cyj.phone_storedemo.vo.DataVO;
import com.cyj.phone_storedemo.vo.PhoneInfoVO;
import com.cyj.phone_storedemo.vo.SpecsPackageVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhoneServiceImplTest {

    @Autowired
    private PhoneService phoneService;

    @Test
    void findDataVO() {
        DataVO dataVO = phoneService.findDataVO();
        int id = 1;

    }

    @Test
    void findPhoneInfoVOByCategoryType() {
        List<PhoneInfoVO> phoneInfoVOList = phoneService.findPhoneInfoVOByCategoryType(2);
        int id = 1;
    }

    @Test
    void findSpecsPackageVOByPhoneId() {
        SpecsPackageVO specsPackageVO = phoneService.findSpecsPackageVOByPhoneId(1);
        int id = 1;
    }

    @Test
    void subStock(){
        phoneService.subStock(2,3);
    }
}