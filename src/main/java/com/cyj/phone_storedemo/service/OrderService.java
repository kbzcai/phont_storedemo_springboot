package com.cyj.phone_storedemo.service;

import com.cyj.phone_storedemo.dto.OrderDTO;
import com.cyj.phone_storedemo.vo.OrderDetailVO;

public interface OrderService {
    public OrderDTO save(OrderDTO orderDTO);
    public OrderDetailVO findOrderDetailByOrderId(String orderId);
    public String pay(String orderId);
}
