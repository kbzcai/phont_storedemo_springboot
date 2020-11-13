package com.cyj.phone_storedemo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 收货地址表
 * </p>
 *
 * @author cyj
 * @since 2020-11-12
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
public class BuyerAddress implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "address_id", type = IdType.AUTO)
      private Integer addressId;

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
     * 地址编码
     */
      private String areaCode;

      /**
     * 修改时间
     */
      @TableField(fill = FieldFill.INSERT_UPDATE)
      private LocalDateTime updateTime;

      /**
     * 创建时间
     */
      @TableField(fill = FieldFill.INSERT)
      private LocalDateTime createTime;


}
