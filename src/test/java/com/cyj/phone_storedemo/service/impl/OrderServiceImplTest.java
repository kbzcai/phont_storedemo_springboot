package com.cyj.phone_storedemo.service.impl;

import com.cyj.phone_storedemo.dto.OrderDTO;
import com.cyj.phone_storedemo.service.OrderService;
import com.cyj.phone_storedemo.vo.OrderDetailVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Test
    void save() {
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setBuyerName("张三");
        orderDTO.setBuyerPhone("13678787878");
        orderDTO.setBuyerAddress("广东省深圳市罗湖区科技路123号456室");
        orderDTO.setSpecsId(1);
        orderDTO.setPhoneQuantity(1);
        OrderDTO result=orderService.save(orderDTO);
        System.out.println(result);
    }

    @Test
    void findOrderDetailByOrderId(){
        OrderDetailVO orderDetailVO=orderService.findOrderDetailByOrderId("1605519197941979305");
        int i=1;
    }

    @Test
    void pay(){
        orderService.pay("1605519450185876850");
    }
}