package com.bondsales.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BidCreateVo {

    private String cusip;

    private String productName;

    private String productDescription;

    private double startingValue;

    private double minimumBidIncrement;

    private Date auctionDeadline;

}
