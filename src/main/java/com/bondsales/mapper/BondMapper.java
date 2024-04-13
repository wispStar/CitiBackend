package com.bondsales.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bondsales.entity.Bond;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BondMapper extends BaseMapper<Bond> {
    @Select("SELECT p.cusip,\n" +
            "       MAX(p.id) as id,\n" +
            "       MAX(p.productName) as productName,\n" +
            "       MAX(p.productDescription) as productDescription,\n" +
            "       MAX(p.position) as position,\n" +
            "       MAX(p.startingValue) as startingValue,\n" +
            "       MAX(bd.bidValue) as bidValue,\n" +
            "       MAX(p.minimumBidIncrement) as minimumBidIncrement,\n" +
            "       MAX(p.auctionDeadline) as auctionDeadline\n" +
            "FROM product p LEFT JOIN bid bd ON p.cusip = bd.cusip\n" +
            "GROUP BY p.cusip\n" +
            "ORDER BY id;")
    List<Bond> selectBondAndBidInfo(String username);
}
