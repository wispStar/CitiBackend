package com.bondsales.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BidDetailsVo {
    private ProductInfoVo productInfo;
    private List<UserBidsVo> userBidsList;
}
