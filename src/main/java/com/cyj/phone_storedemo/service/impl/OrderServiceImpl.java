package com.cyj.phone_storedemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyj.phone_storedemo.dto.OrderDTO;
import com.cyj.phone_storedemo.entity.OrderMaster;
import com.cyj.phone_storedemo.entity.PhoneInfo;
import com.cyj.phone_storedemo.entity.PhoneSpecs;
import com.cyj.phone_storedemo.enums.PayStatusEnum;
import com.cyj.phone_storedemo.enums.ResultEnum;
import com.cyj.phone_storedemo.exception.PhoneException;
import com.cyj.phone_storedemo.mapper.OrderMasterMapper;
import com.cyj.phone_storedemo.mapper.PhoneInfoMapper;
import com.cyj.phone_storedemo.mapper.PhoneSpecsMapper;
import com.cyj.phone_storedemo.service.OrderService;
import com.cyj.phone_storedemo.service.PhoneService;
import com.cyj.phone_storedemo.util.KeyUtil;
import com.cyj.phone_storedemo.vo.OrderDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterMapper orderMasterMapper;

    @Autowired
    private PhoneSpecsMapper phoneSpecsMapper;

    @Autowired
    private PhoneInfoMapper phoneInfoMapper;

    @Autowired
    private PhoneService phoneService;

    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);

        PhoneSpecs phoneSpecs = phoneSpecsMapper.selectById(orderDTO.getSpecsId());
        if (orderMaster == null) {
            log.error("【创建订单】规格不存在,phoneSpecs={}", phoneSpecs);
            throw new PhoneException(ResultEnum.SPECS_NOT_EXIST);
        }
        BeanUtils.copyProperties(phoneSpecs, orderMaster);

        PhoneInfo phoneInfo = phoneInfoMapper.selectById(phoneSpecs.getPhoneId());
        if (orderMaster == null) {
            log.error("【创建订单】手机不存在,phoneInfo={}", phoneInfo);
            throw new PhoneException(ResultEnum.PHONE_NOT_EXIST);
        }
        BeanUtils.copyProperties(phoneInfo, orderMaster);

        //计算总价
        BigDecimal orderAmount = new BigDecimal(0);
        orderAmount = phoneSpecs.getSpecsPrice()
                .divide(new BigDecimal(100))
                .multiply(new BigDecimal(orderDTO.getPhoneQuantity())).add(orderAmount)
                .add(new BigDecimal(10));
        orderMaster.setOrderAmount(orderAmount);

        //orderId
        orderMaster.setOrderId(KeyUtil.createUniqueKey());
        orderDTO.setOrderId(orderMaster.getOrderId());
        //payStatus
        orderMaster.setPayStatus(PayStatusEnum.UNPAID.getCode());
        orderMasterMapper.insert(orderMaster);
        phoneService.subStock(orderDTO.getSpecsId(), orderDTO.getPhoneQuantity());
        return orderDTO;
    }

    @Override
    public OrderDetailVO findOrderDetailByOrderId(String orderId) {
        OrderDetailVO orderDetailVO = new OrderDetailVO();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("order_id", orderId);
        OrderMaster orderMaster = orderMasterMapper.selectOne(wrapper);
        if (orderMaster == null) {
            log.error("【查询订单】订单不存在,orderMaster={}", orderMaster);
            throw new PhoneException(ResultEnum.ORDER_NOT_EXIST);
        }
        BeanUtils.copyProperties(orderMaster, orderDetailVO);
        orderDetailVO.setSpecsPrice(orderMaster.getSpecsPrice().divide(new BigDecimal(100)) + ".00");

        return orderDetailVO;
    }

    @Override
    public String pay(String orderId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("order_id", orderId);
        OrderMaster orderMaster = orderMasterMapper.selectOne(wrapper);
        if (orderMaster == null) {
            log.error("【支付订单】订单为空,orderMaster={}", orderMaster);
            throw new PhoneException(ResultEnum.ORDER_NOT_EXIST);
        }
        if (orderMaster.getPayStatus().equals(PayStatusEnum.UNPAID.getCode())) {
            orderMaster.setPayStatus(PayStatusEnum.PAID.getCode());
            orderMasterMapper.update(orderMaster, wrapper);
        } else {
            log.error("【支付订单】订单已支付,orderMaster={}", orderMaster);
        }
        return orderId;
    }
}
