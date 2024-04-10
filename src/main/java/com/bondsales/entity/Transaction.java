package com.bondsales.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Transaction)表实体类
 *
 * @author makejava
 * @since 2024-04-10 22:13:07
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("transaction")
public class Transaction  {
    
    @TableId
    private Integer id;


    private String cusip;

    private String username;

    private String paymentmethod;

    private Double transactionamount;

    private Date transactiontime;

    private String transactiontype;
    
}
