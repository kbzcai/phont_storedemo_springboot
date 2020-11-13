package com.cyj.phone_storedemo.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author cyj
 * @since 2020-11-12
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
public class OrderMaster implements Serializable {

    private static final long serialVersionUID=1L;

      private String orderId;

      /**
     * 买家名字
     */
      private String buyerName;

      /**
     * 买家电话
     */
      private String buyerPhone;

      /**
     * 买家地址
     */
      private String buyerAddress;

      /**
     * 商品编号
     */
      private Integer phoneId;

      /**
     * 商品名称
     */
      private String phoneName;

      /**
     * 商品数量
     */
      private Integer phoneQuantity;

      /**
     * 商品小图
     */
      private String phoneIcon;

      /**
     * 规格编号
     */
      private Integer specsId;

      /**
     * 规格名称
     */
      private String specsName;

      /**
     * 规格单价
     */
      private BigDecimal specsPrice;

      /**
     * 订单总金额
     */
      private BigDecimal orderAmount;

      /**
     * 支付状态，默认0未支付
     */
      private Integer payStatus;

      /**
     * 创建时间
     */
      @TableField(fill = FieldFill.INSERT)
      private LocalDateTime createTime;

      /**
     * 修改时间
     */
      @TableField(fill = FieldFill.INSERT_UPDATE)
      private LocalDateTime updateTime;


}
