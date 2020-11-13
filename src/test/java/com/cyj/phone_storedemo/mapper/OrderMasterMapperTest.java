package com.cyj.phone_storedemo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyj.phone_storedemo.entity.OrderMaster;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderMasterMapperTest {

    @Autowired
    private OrderMasterMapper orderMasterMapper;

    @Test
    void findAll(){
        orderMasterMapper.selectList(null).forEach(System.out::println);
    }

    @Test
    void save(){
        OrderMaster orderMaster=new OrderMaster();
        orderMaster.setOrderId("123456");
        orderMaster.setBuyerName("小红");
        orderMaster.setBuyerAddress("重庆市九龙坡区");
        orderMaster.setBuyerPhone("15123336960");
        orderMaster.setOrderAmount(new BigDecimal(6400));
        orderMaster.setPayStatus(0);
        orderMaster.setPhoneIcon("../static/e84a2e03-7f19-41d2-98a5-a5c16b7e252d.jpg");
        orderMaster.setPhoneId(1);
        orderMaster.setPhoneName("Honor 8A");
        orderMaster.setPhoneQuantity(2);
        orderMaster.setSpecsId(1);
        orderMaster.setSpecsName("32GB");
        orderMaster.setSpecsPrice(new BigDecimal(320000));
        orderMasterMapper.insert(orderMaster);
    }

    @Test
    void findById(){
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("order_id","123456");
        System.out.println(orderMasterMapper.selectOne(wrapper));
    }

    @Test
    void pay(){
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("order_id","123456");
        OrderMaster orderMaster=orderMasterMapper.selectOne(wrapper);
        orderMaster.setPayStatus(1);
        orderMasterMapper.update(orderMaster,wrapper);
    }
}