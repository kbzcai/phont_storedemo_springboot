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
 * 类目表
 * </p>
 *
 * @author cyj
 * @since 2020-11-12
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
public class PhoneCategory implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "category_id", type = IdType.AUTO)
      private Integer categoryId;

      /**
     * 类目名称
     */
      private String categoryName;

      /**
     * 类目编号
     */
      private Integer categoryType;

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
