package com.cyj.phone_storedemo.entity;

import java.math.BigDecimal;

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
 * 商品规格表
 * </p>
 *
 * @author cyj
 * @since 2020-11-12
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
public class PhoneSpecs implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "specs_id", type = IdType.AUTO)
      private Integer specsId;

    private String phoneId;

      /**
     * 规格名称
     */
      private String specsName;

      /**
     * 库存
     */
      private Integer specsStock;

      /**
     * 单价
     */
      private BigDecimal specsPrice;

      /**
     * 小图
     */
      private String specsIcon;

      /**
     * 预览图
     */
      private String specsPreview;

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
