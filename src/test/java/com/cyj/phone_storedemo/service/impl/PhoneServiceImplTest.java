package com.cyj.phone_storedemo.service.impl;

import com.cyj.phone_storedemo.service.PhoneService;
import com.cyj.phone_storedemo.vo.DataVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhoneServiceImplTest {

    @Autowired
    private PhoneService phoneService;

    @Test
    void findDataVO() {
        DataVO dataVO=phoneService.findDataVO();
        int id=0;

    }
}