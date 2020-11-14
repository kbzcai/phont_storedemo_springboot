package com.cyj.phone_storedemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyj.phone_storedemo.entity.PhoneCategory;
import com.cyj.phone_storedemo.entity.PhoneInfo;
import com.cyj.phone_storedemo.entity.PhoneSpecs;
import com.cyj.phone_storedemo.enums.ResultEnum;
import com.cyj.phone_storedemo.exception.PhoneException;
import com.cyj.phone_storedemo.mapper.PhoneCategoryMapper;
import com.cyj.phone_storedemo.mapper.PhoneInfoMapper;
import com.cyj.phone_storedemo.mapper.PhoneSpecsMapper;
import com.cyj.phone_storedemo.service.PhoneService;
import com.cyj.phone_storedemo.util.PhoneUtil;
import com.cyj.phone_storedemo.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Slf4j//打印日志
public class PhoneServiceImpl implements PhoneService {
    @Autowired
    private PhoneCategoryMapper phoneCategoryMapper;

    @Autowired
    private PhoneInfoMapper phoneInfoMapper;

    @Autowired
    private PhoneSpecsMapper phoneSpecsMapper;

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
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("category_type", phoneCategoryList.get(0).getCategoryType());
        List<PhoneInfo> phoneInfoList = phoneInfoMapper.selectList(wrapper);
//        List<PhoneInfoVO> phoneInfoVOList=new ArrayList<PhoneInfoVO>();
//        for (PhoneInfo phoneInfo:phoneInfoList){
//            PhoneInfoVO phoneInfoVO=new PhoneInfoVO();
//            BeanUtils.copyProperties(phoneInfo,phoneInfoVO);
//            phoneInfoVO.setTag(PhoneUtil.createTag(phoneInfo.getPhoneTag()));
//            phoneInfoVOList.add(phoneInfoVO);
//        }
        //stream流写法
        List<PhoneInfoVO> phoneInfoVOList = phoneInfoList.stream()
                .map(e -> new PhoneInfoVO(
                        e.getPhoneId(),
                        e.getPhoneName(),
                        e.getPhonePrice() + ".00",
                        e.getPhoneDescription(),
                        PhoneUtil.createTag(e.getPhoneTag()),
                        e.getPhoneIcon()
                )).collect(Collectors.toList());
        dataVO.setPhones(phoneInfoVOList);
        return dataVO;
    }

    @Override
    public List<PhoneInfoVO> findPhoneInfoVOByCategoryType(Integer categoryType) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("category_type", categoryType);
        List<PhoneInfo> phoneInfoList = phoneInfoMapper.selectList(wrapper);
        List<PhoneInfoVO> phoneInfoVOList = phoneInfoList.stream()
                .map(e -> new PhoneInfoVO(
                        e.getPhoneId(),
                        e.getPhoneName(),
                        e.getPhonePrice() + ".00",
                        e.getPhoneDescription(),
                        PhoneUtil.createTag(e.getPhoneTag()),
                        e.getPhoneIcon()
                )).collect(Collectors.toList());
        return phoneInfoVOList;
    }

    @Override
    public SpecsPackageVO findSpecsPackageVOByPhoneId(Integer phoneId) {
        PhoneInfo phoneInfo = phoneInfoMapper.selectById(phoneId);
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("phone_id", phoneId);
        List<PhoneSpecs> phoneSpecsList = phoneSpecsMapper.selectList(wrapper);

        //封装Tree
        List<VVO> vvoList = new ArrayList<VVO>();
        List<ListVO> listVOList = new ArrayList<ListVO>();
        VVO vvo;
        ListVO listVO;
        for (PhoneSpecs phoneSpecs : phoneSpecsList) {
            vvo = new VVO();
            listVO = new ListVO();
            BeanUtils.copyProperties(phoneSpecs, vvo);
            BeanUtils.copyProperties(phoneSpecs, listVO);
            vvoList.add(vvo);
            listVOList.add(listVO);
        }
        TreeVO treeVO = new TreeVO();
        treeVO.setV(vvoList);
        List<TreeVO> treeVOList = new ArrayList<TreeVO>();
        treeVOList.add(treeVO);

        //封装Sku
        SkuVO skuVO = new SkuVO();
        skuVO.setTree(treeVOList);
        skuVO.setList(listVOList);
        Integer price = phoneInfo.getPhonePrice().intValue();
        skuVO.setPrice(price + ".00");
        skuVO.setStock_num(phoneInfo.getPhoneStock());

        //封装SpecsPackage
        SpecsPackageVO specsPackageVO = new SpecsPackageVO();
        specsPackageVO.setSku(skuVO);
        Map<String, String> goods = new HashMap<>();
        goods.put("picture", phoneInfo.getPhoneIcon());
        specsPackageVO.setGoods(goods);
        return specsPackageVO;
    }

    @Override
    public void subStock(Integer specsId, Integer quantity) {
        PhoneSpecs phoneSpecs = phoneSpecsMapper.selectById(specsId);
        PhoneInfo phoneInfo = phoneInfoMapper.selectById(phoneSpecs.getPhoneId());
        Integer result = phoneSpecs.getSpecsStock() - quantity;
        if (result < 0) {
            log.error("【扣库存】库存不足");
            throw new PhoneException(ResultEnum.PHONE_STOCK_ERROR);
        }
        phoneSpecs.setSpecsStock(result);
        phoneSpecsMapper.updateById(phoneSpecs);
        result = phoneInfo.getPhoneStock() - quantity;
        if (result < 0) {
            log.error("【扣库存】库存不足");
            throw new PhoneException(ResultEnum.PHONE_STOCK_ERROR);
        }
        phoneInfo.setPhoneStock(result);
        phoneInfoMapper.updateById(phoneInfo);
    }
}
