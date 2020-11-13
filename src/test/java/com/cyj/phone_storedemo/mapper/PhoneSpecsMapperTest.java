package com.cyj.phone_storedemo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhoneSpecsMapperTest {

    @Autowired
    private PhoneSpecsMapper phoneSpecsMapper;

    @Test
    void findAll(){
        phoneSpecsMapper.selectList(null).forEach(System.out::println);
    }

    @Test
    void findAllByPhoneId(){
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("phone_id",1);
        phoneSpecsMapper.selectList(wrapper).forEach(System.out::println);
    }

}