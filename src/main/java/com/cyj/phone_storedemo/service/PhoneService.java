package com.cyj.phone_storedemo.service;

import com.cyj.phone_storedemo.vo.DataVO;
import com.cyj.phone_storedemo.vo.PhoneInfoVO;
import com.cyj.phone_storedemo.vo.SpecsPackageVO;

import java.util.List;

public interface PhoneService {
    public DataVO findDataVO();
    public List<PhoneInfoVO> findPhoneInfoVOByCategoryType(Integer categoryType);
    public SpecsPackageVO findSpecsPackageVOByPhoneId(Integer phoneId);
    public void subStock(Integer specsId,Integer quantity);
}
