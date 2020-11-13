package com.cyj.phone_storedemo.mapper;

import com.cyj.phone_storedemo.entity.BuyerAddress;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BuyerAddressMapperTest {

    @Autowired
    private BuyerAddressMapper buyerAddressMapper;

    @Test
    void findAll() {
        buyerAddressMapper.selectList(null).forEach(System.out::println);
    }

    @Test
    void save() {
        BuyerAddress buyerAddress = new BuyerAddress();
        buyerAddress.setAreaCode("110101");
        buyerAddress.setBuyerAddress("重庆市九龙坡区");
        buyerAddress.setBuyerName("小红");
        buyerAddress.setBuyerPhone("13618281862");
        buyerAddressMapper.insert(buyerAddress);
    }

    @Test
    void update(){
        BuyerAddress buyerAddress=buyerAddressMapper.selectById(35);
        buyerAddress.setBuyerName("小黑");
        buyerAddressMapper.updateById(buyerAddress);
    }
}