package com.bondsales.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("product")
public class Bond {
    //主键
    @TableId
    private Long id;

    //CUISP认证标识
    private String cusip;
    //产品名称
    private String productname;
    //产品内容描述
    private String productdescription;
    //持仓，产品总金额
    private int position;
    //起始价格
    private double startingvalue;
    //当前价格
    @TableField(exist = false)
    private int bidvalue;
    //最低出价增量
    private double minimumbidincrement;
    //地址
    private Date auctiondeadline;
    //排名
    @TableField(exist = false)
    private String rank;

    public String getProductName() {
        return productname;
    }

    public String getProductDescription() {
        return productdescription;
    }

    public double getStartingValue() {
        return startingvalue;
    }

    public double getMinimumBidIncrement() {
        return minimumbidincrement;
    }

    public Date getAuctionDeadline() {
        return auctiondeadline;
    }
}
