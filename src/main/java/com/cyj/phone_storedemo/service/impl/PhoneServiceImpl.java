package com.cyj.phone_storedemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyj.phone_storedemo.entity.PhoneCategory;
import com.cyj.phone_storedemo.entity.PhoneInfo;
import com.cyj.phone_storedemo.mapper.PhoneCategoryMapper;
import com.cyj.phone_storedemo.mapper.PhoneInfoMapper;
import com.cyj.phone_storedemo.service.PhoneService;
import com.cyj.phone_storedemo.util.PhoneUtil;
import com.cyj.phone_storedemo.vo.DataVO;
import com.cyj.phone_storedemo.vo.PhoneCategoryVO;
import com.cyj.phone_storedemo.vo.PhoneInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class PhoneServiceImpl implements PhoneService {
    @Autowired
    private PhoneCategoryMapper phoneCategoryMapper;

    @Autowired
    private PhoneInfoMapper phoneInfoMapper;

    @Override
    public DataVO findDataVO() {
        DataVO dataVO = new DataVO();
        //类型
        List<PhoneCategory> phoneCategoryList = phoneCategoryMapper.selectList(null);
        //常规写法
//        List<PhoneCategoryVO> phoneCategoryVOList=new ArrayList<PhoneCategoryVO>();
//        for (PhoneCategory phoneCategory:phoneCategoryList){
//            PhoneCategoryVO phoneCategoryVO=new PhoneCategoryVO();
//            phoneCategoryVO.setCategoryName(phoneCategory.getCategoryName());
//            phoneCategoryVO.setCategoryType(phoneCategory.getCategoryType());
//            phoneCategoryVOList.add(phoneCategoryVO);
//        }
        //stream流写法
        List<PhoneCategoryVO> phoneCategoryVOList = phoneCategoryList.stream()
                .map(e -> new PhoneCategoryVO(
                        e.getCategoryName(),
                        e.getCategoryType()
                )).collect(Collectors.toList());
        dataVO.setCategoies(phoneCategoryVOList);
        //手机
        //常规写法
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("category_type",phoneCategoryList.get(0).getCategoryType());
        List<PhoneInfo> phoneInfoList=phoneInfoMapper.selectList(wrapper);
//        List<PhoneInfoVO> phoneInfoVOList=new ArrayList<PhoneInfoVO>();
//        for (PhoneInfo phoneInfo:phoneInfoList){
//            PhoneInfoVO phoneInfoVO=new PhoneInfoVO();
//            BeanUtils.copyProperties(phoneInfo,phoneInfoVO);
//            phoneInfoVO.setTag(PhoneUtil.createTag(phoneInfo.getPhoneTag()));
//            phoneInfoVOList.add(phoneInfoVO);
//        }
        //stream流写法
        List<PhoneInfoVO> phoneInfoVOList=phoneInfoList.stream()
                .map(e->new PhoneInfoVO(e.getPhoneId(),e.getPhoneName(),e.getPhonePrice(),e.getPhoneDescription(),PhoneUtil.createTag(e.getPhoneTag()),e.getPhoneIcon())).collect(Collectors.toList());
        dataVO.setPhones(phoneInfoVOList);
        return dataVO;
    }
}
