package com.cyj.phone_storedemo.controller;

import com.cyj.phone_storedemo.exception.PhoneException;
import com.cyj.phone_storedemo.form.AddressForm;
import com.cyj.phone_storedemo.service.AddressService;
import com.cyj.phone_storedemo.util.ResultVOUtil;
import com.cyj.phone_storedemo.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/address")
@Slf4j
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/list")
    public ResultVO list(){
        return ResultVOUtil.success(addressService.findAll());
    }

    @PostMapping("/create")//数据校验
    public ResultVO create(@Valid @RequestBody AddressForm addressForm, BindingResult bindingResult){
        if ((bindingResult.hasErrors())){
            log.error("【添加地址】参数错误,addressForm={}",addressForm);
            throw new PhoneException(bindingResult.getFieldError().getDefaultMessage());
        }else {
            addressService.saveOrUpdate(addressForm);
        }
        return ResultVOUtil.success(null);
    }

    @PutMapping("/update")
    public ResultVO update(@Valid @RequestBody AddressForm addressForm,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【修改地址】参数错误,addressFrom={}",addressForm);
            throw new PhoneException(bindingResult.getFieldError().getDefaultMessage());
        }else {
            addressService.saveOrUpdate(addressForm);
        }
        return ResultVOUtil.success(null);
    }
}
