package com.cyj.phone_storedemo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhoneCategoryMapperTest {

    @Autowired
    private PhoneCategoryMapper phoneCategoryMapper;

    @Test
    void test(){
        phoneCategoryMapper.selectList(null).forEach(System.out::println);
    }

    @Test
    void findByCategoryType(){
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("category_type",1);
        System.out.println(phoneCategoryMapper.selectOne(wrapper));
    }
}