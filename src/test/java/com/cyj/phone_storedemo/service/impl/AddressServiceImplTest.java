package com.cyj.phone_storedemo.service.impl;

import com.cyj.phone_storedemo.form.AddressForm;
import com.cyj.phone_storedemo.service.AddressService;
import com.cyj.phone_storedemo.vo.AddressVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddressServiceImplTest {


    @Autowired
    private AddressService addressService;

    @Test
    void findAll() {
        List<AddressVO> addressVOList=addressService.findAll();
        int i=1;
    }

    @Test
    void saveOrUpdate(){
        AddressForm addressForm=new AddressForm();
        addressForm.setId(36);
        addressForm.setName("小王");
        addressForm.setTel("13678900987");
        addressForm.setProvince("北京市");
        addressForm.setCity("北京市");
        addressForm.setCounty("东城区");
        addressForm.setAreaCode("110101");
        addressForm.setAddressDetail("168号306室");
        addressService.saveOrUpdate(addressForm);
    }
}