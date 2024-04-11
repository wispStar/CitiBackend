package com.bondsales.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bondsales.ResponseResult;
import com.bondsales.constants.SystemConstants;
import com.bondsales.entity.Bid;
import com.bondsales.entity.Bond;
import com.bondsales.enums.AppHttpCodeEnum;
import com.bondsales.exception.SystemException;
import com.bondsales.mapper.BidMapper;
import com.bondsales.mapper.BondMapper;
import com.bondsales.service.BidService;
import com.bondsales.utils.BeanCopyUtils;
import com.bondsales.vo.BidCreateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (Bid)表服务实现类
 *
 * @author makejava
 * @since 2024-04-10 22:07:43
 */
@Service("bidService")
public class BidServiceImpl extends ServiceImpl<BidMapper, Bid> implements BidService {

    @Autowired
    private BondMapper bondMapper;

    @Autowired
    private BidMapper bidMapper;

    @Override
    public ResponseResult create(String cusip) {
        // 根据cusip查询该债券在数据库中的信息
        LambdaQueryWrapper<Bond> queryWrapper =new LambdaQueryWrapper<>();
        queryWrapper.eq(Bond::getCusip, cusip);
        Bond bond = bondMapper.selectOne(queryWrapper);

        // 债券不存在
        if (bond == null) {
            throw new SystemException(AppHttpCodeEnum.BOND_NOT_EXISTS);
        } else {
            // 债券存在, 封装信息返回
            //        数据库    实体类    Vo
            // 正常   下划线     驼峰     驼峰
            // 现在   驼峰       小写     驼峰
            BidCreateVo bidCreateVo = BeanCopyUtils.copyBean(bond, BidCreateVo.class);

            return ResponseResult.okResult(bidCreateVo);
        }
    }

    @Override
    public ResponseResult delete(String cusip, String username) {
        // 查询数据库中信息
        LambdaQueryWrapper<Bid> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Bid::getCusip, cusip);
        queryWrapper.eq(Bid::getUsername, username);
        Bid bid = bidMapper.selectOne(queryWrapper);

        // 判断是否存在符合要求的bid
        if (bid == null) {
            throw new SystemException(AppHttpCodeEnum.DELETE_BOND_NOT_FOUND);
        } else {
            // 执行数据库删除操作
            int rows = bidMapper.delete(queryWrapper);
            if (rows > 0) {
                return ResponseResult.okResult();
            } else {
                // 删除失败处理
                throw new SystemException(AppHttpCodeEnum.DELETE_BID_FAILED);
            }
        }
    }
}
