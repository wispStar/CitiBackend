package com.bondsales.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bondsales.ResponseResult;
import com.bondsales.entity.Bond;
import com.bondsales.entity.User;
import com.bondsales.enums.AppHttpCodeEnum;
import com.bondsales.exception.SystemException;
import com.bondsales.mapper.BondMapper;
import com.bondsales.service.BondService;
import com.bondsales.vo.BondShowVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("bondService")
public class BondServiceImpl extends ServiceImpl<BondMapper, Bond> implements BondService {

    @Autowired
    private BondMapper bondMapper;

    @Override
    public ResponseResult showAll(){
        // 使用 bondMapper 的 selectList 方法获取所有的 Bond 对象
        List<Bond> bonds = bondMapper.selectList(null);

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
            bondShowVo.setMinimumBidIncrement(bond.getMinimumBidIncrement());
            bondShowVo.setAuctionDeadline(bond.getAuctionDeadline());

            // 将 BondShowVo 对象添加到列表中
            bondShowVos.add(bondShowVo);
        }
        // 创建一个 ResponseResult 对象，将查询到的 Bond 对象列表作为数据设置到这个对象中
        ResponseResult result = ResponseResult.okResult(bondShowVos);

        // 返回创建的 ResponseResult 对象
        return result;
    }

    public boolean bondExist(String cusip) {
        LambdaQueryWrapper<Bond> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Bond::getCusip, cusip);
        return  count(queryWrapper) > 0;
    }
}
