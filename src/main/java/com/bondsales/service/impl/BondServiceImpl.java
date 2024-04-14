package com.bondsales.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bondsales.ResponseResult;
import com.bondsales.entity.Bid;
import com.bondsales.entity.Bond;
import com.bondsales.enums.AppHttpCodeEnum;
import com.bondsales.exception.SystemException;
import com.bondsales.mapper.BidMapper;
import com.bondsales.mapper.BondMapper;
import com.bondsales.service.BondService;
import com.bondsales.utils.BeanCopyUtils;
import com.bondsales.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service("bondService")
public class BondServiceImpl extends ServiceImpl<BondMapper, Bond> implements BondService {

    @Autowired
    private BondMapper bondMapper;

    @Autowired
    private BidMapper bidMapper;

    @Override
    public ResponseResult showAll(UserNameVo userNameVo){
        String username = userNameVo.getUsername();

        // 使用自定义方法selectBondAndBidInfo查找所需数值
        List<Bond> bonds = bondMapper.selectBondAndBidInfo(username);

//        QueryWrapper<Bond> queryWrapper = new QueryWrapper<>();
//        queryWrapper.select("cusip","productname","productdescription","position","startingvalue","minimumbidincrement","auctiondeadline");
//        // 使用带有 QueryWrapper 参数的 selectList 方法进行查询
//        List<Bond> bonds = bondMapper.selectList(queryWrapper);
        if (bonds == null) {
            throw new SystemException(AppHttpCodeEnum.BOND_NOT_FOUND);
        }

        // 创建一个新的 BondShowVo 列表
        List<BondShowVo> bondShowVos = new ArrayList<BondShowVo>();

        // 遍历 bonds 列表
        for (Bond bond : bonds) {
            // 创建一个新的 BondShowVo 对象
            BondShowVo bondShowVo = new BondShowVo();

            // 将 Bond 对象的属性复制到 BondShowVo 对象
            // 这里假设 BondShowVo 类有相应的 setter 方法
            bondShowVo.setCusip(bond.getCusip());
            bondShowVo.setProductName(bond.getProductName());
            bondShowVo.setProductDescription(bond.getProductDescription());
            bondShowVo.setPosition(bond.getPosition());
            bondShowVo.setStartingValue(bond.getStartingValue());
            if(bond.getBidvalue() != 0)
                bondShowVo.setBidValue(bond.getBidvalue());
            bondShowVo.setMinimumBidIncrement(bond.getMinimumBidIncrement());
            bondShowVo.setAuctionDeadline(bond.getAuctionDeadline());

            // 查询用户在这个bond上的竞价记录
            LambdaQueryWrapper<Bid> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Bid::getCusip, bond.getCusip());
            wrapper.eq(Bid::getUsername, username);
            Bid userBid = bidMapper.selectOne(wrapper);

            // 如果用户在这个bond上有竞价记录
            if (userBid != null) {
                // 查询所有在这个bond上的竞价记录，并按照竞价金额降序排序
                wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(Bid::getCusip, bond.getCusip());
                wrapper.orderByDesc(Bid::getBidvalue);
                List<Bid> allBids = bidMapper.selectList(wrapper);

                // 计算用户的排名
                int ranking = allBids.indexOf(userBid) + 1;
                bondShowVo.setRanking(ranking + "/" + allBids.size());
            } else {
                // 如果用户在这个bond上没有竞价记录，设置ranking为You haven't bid yet!
                bondShowVo.setRanking("Haven't bid yet");
            }

            // 将 BondShowVo 对象添加到列表中
            bondShowVos.add(bondShowVo);
        }

        // 创建一个 ResponseResult 对象，将查询到的 Bond 对象列表作为数据设置到这个对象中
        ResponseResult result = ResponseResult.okResult(bondShowVos);

        // 返回创建的 ResponseResult 对象
        return result;
    }

    @Override
    public ResponseResult addProduct(Bond bond){

        System.out.println("bond:"+bond);
        // 对数据进行进行非空判断
        if (!StringUtils.hasText(bond.getCusip())) {
            throw new SystemException(AppHttpCodeEnum.INVALID_NULL);
        }
        if (!StringUtils.hasText(bond.getProductName())) {
            throw new SystemException(AppHttpCodeEnum.INVALID_NULL);
        }
        if (!StringUtils.hasText(bond.getProductDescription())) {
            throw new SystemException(AppHttpCodeEnum.INVALID_NULL);
        }
        if (bond.getStartingValue() == 0) {
            throw new SystemException(AppHttpCodeEnum.INVALID_NULL);
        }
        if (bond.getMinimumBidIncrement() == 0) {
            throw new SystemException(AppHttpCodeEnum.INVALID_NULL);
        }
        if (bond.getAuctionDeadline() == null) {
            throw new SystemException(AppHttpCodeEnum.INVALID_NULL);
        }

        if (bondExist(bond.getCusip())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.BOND_EXISTS);
        } else {
            // 债券不存在，将债券信息存入数据库
            save(bond);

            // 封装返回数据
            BondAddVo bondAddVo = BeanCopyUtils.copyBean(bond, BondAddVo.class);
            return ResponseResult.okResult(bondAddVo);
        }
    }

    public boolean bondExist(String cusip) {
        LambdaQueryWrapper<Bond> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Bond::getCusip, cusip);
        return  count(queryWrapper) > 0;
    }
}
