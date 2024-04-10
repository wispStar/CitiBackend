package com.bondsales.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bondsales.ResponseResult;
import com.bondsales.entity.Bid;
import com.bondsales.enums.AppHttpCodeEnum;
import com.bondsales.exception.SystemException;
import com.bondsales.mapper.BidMapper;
import com.bondsales.service.BidService;
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
    private BidMapper bidMapper;

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
