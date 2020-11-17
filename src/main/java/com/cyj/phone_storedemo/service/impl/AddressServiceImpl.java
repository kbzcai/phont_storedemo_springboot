package com.cyj.phone_storedemo.service.impl;

import com.cyj.phone_storedemo.entity.BuyerAddress;
import com.cyj.phone_storedemo.form.AddressForm;
import com.cyj.phone_storedemo.mapper.BuyerAddressMapper;
import com.cyj.phone_storedemo.service.AddressService;
import com.cyj.phone_storedemo.vo.AddressVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private BuyerAddressMapper buyerAddressMapper;

    @Override
    public List<AddressVO> findAll() {
        List<AddressVO> addressVOList=
                buyerAddressMapper.selectList(null)
                        .stream()
                        .map(e -> new AddressVO(e.getAddressId(),e.getAreaCode(),e.getBuyerName(),e.getBuyerPhone(),e.getBuyerAddress()))
                        .collect(Collectors.toList());
        return addressVOList;
    }

    @Override
    public void saveOrUpdate(AddressForm addressForm) {
        BuyerAddress buyerAddress;
        if (addressForm.getId()==null){
            buyerAddress = new BuyerAddress();
        }else{
            buyerAddress=buyerAddressMapper.selectById(addressForm.getId());
        }
        buyerAddress.setBuyerName(addressForm.getName());
        buyerAddress.setBuyerPhone(addressForm.getTel());
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append(addressForm.getProvince())
                .append(addressForm.getCity())
                .append(addressForm.getCounty())
                .append(addressForm.getAddressDetail());
        buyerAddress.setBuyerAddress(stringBuffer.toString());
        buyerAddress.setAreaCode(addressForm.getAreaCode());
        if (addressForm.getId()==null) {
            buyerAddressMapper.insert(buyerAddress);
        }else{
            buyerAddressMapper.updateById(buyerAddress);
        }
    }
}
