package com.bondsales.entity;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Bid)表实体类
 *
 * @author makejava
 * @since 2024-04-10 22:07:43
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("bid")
public class Bid  {
    
    @TableId
    private Integer id;

    private String cusip;

    private String username;

    private Double bidvalue;
    
    private Date bidtime;
    
}
