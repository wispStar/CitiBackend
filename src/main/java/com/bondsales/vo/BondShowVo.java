package com.bondsales.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BondShowVo {
    //CUISP认证标识
    private String cusip;
    //产品名称
    private String productName;
    //产品内容描述
    private String productDescription;
    //持仓，产品总金额
    private int position;
    //起始价格
    private double startingValue;
    //当前价格
    private int bidValue;
    //最低出价增量
    private double minimumBidIncrement;
    //地址
    private Date auctionDeadline;
    //排名
    private String ranking;

    public void setProductName(String productname) {
        this.productName = productname;
    }

    public void setProductDescription(String productdescription) {
        this.productDescription = productdescription;
    }

    public void setStartingValue(double startingvalue) {
        this.startingValue = startingvalue;
    }

    public void setMinimumBidIncrement(double minimumbidincrement) {
        this.minimumBidIncrement = minimumbidincrement;
    }

    public void setAuctionDeadline(Date auctiondeadline) {
        this.auctionDeadline = auctiondeadline;
    }
}


