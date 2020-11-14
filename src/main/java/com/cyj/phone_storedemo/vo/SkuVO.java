package com.cyj.phone_storedemo.vo;

import lombok.Data;

import java.util.List;

@Data
public class SkuVO {
    private List<TreeVO> tree;
    private List<ListVO> list;
    private String price;
    private Integer stock_num;
    private Boolean none_sku = false;
    private Boolean hide_stock = false;
}
