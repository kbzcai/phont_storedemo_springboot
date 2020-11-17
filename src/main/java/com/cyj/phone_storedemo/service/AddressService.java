package com.cyj.phone_storedemo.service;

import com.cyj.phone_storedemo.form.AddressForm;
import com.cyj.phone_storedemo.vo.AddressVO;

import java.util.List;

public interface AddressService {
    public List<AddressVO> findAll();
    public void saveOrUpdate(AddressForm addressForm);
}
