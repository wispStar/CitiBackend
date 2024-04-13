package com.bondsales.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bondsales.ResponseResult;
import com.bondsales.constants.SystemConstants;
import com.bondsales.entity.Bid;
import com.bondsales.entity.Bond;
import com.bondsales.entity.Transaction;
import com.bondsales.entity.User;
import com.bondsales.enums.AppHttpCodeEnum;
import com.bondsales.exception.SystemException;
import com.bondsales.mapper.BidMapper;
import com.bondsales.mapper.BondMapper;
import com.bondsales.mapper.TransactionMapper;
import com.bondsales.service.BidService;
import com.bondsales.service.BondService;
import com.bondsales.service.UserService;
import com.bondsales.utils.BeanCopyUtils;
import com.bondsales.vo.BidCreateVo;
import com.bondsales.vo.BidDetailsVo;
import com.bondsales.vo.ProductInfoVo;
import com.bondsales.vo.UserBidsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

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
    private UserService userService;

    @Autowired
    private BondService bondService;

    @Autowired
    private BidMapper bidMapper;

    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    public ResponseResult create(String cusip) {
        // 对数据进行非空判断
        if (!StringUtils.hasText(cusip)) {
            throw new SystemException(AppHttpCodeEnum.CUSIP_NOT_NULL);
        }

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
    public ResponseResult submit(String cusip, String username, Double bidAmount) {
        // 对数据进行非空判断
        if (!StringUtils.hasText(cusip)) {
            throw new SystemException(AppHttpCodeEnum.CUSIP_NOT_NULL);
        }
        if (!StringUtils.hasText(username)) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        if (bidAmount == null) {
            throw new SystemException(AppHttpCodeEnum.BidAmount_NOT_NULL);
        }

        // 对数据进行存在判断
        if (!bondService.bondExist(cusip)) {
            throw new SystemException(AppHttpCodeEnum.BOND_NOT_EXISTS);
        }
        if (!userService.userNameExist(username)) {
            throw new SystemException(AppHttpCodeEnum.USER_NOT_EXISTS);
        }

        // 向数据库中添加数据
        Bid bid = new Bid();
        bid.setCusip(cusip);
        bid.setUsername(username);
        bid.setBidvalue(bidAmount);
        // 获取当前时间并设置给bidtime字段
        Date currentTime = new Date();
        bid.setBidtime(currentTime);
        bidMapper.insert(bid);

        Transaction transaction = new Transaction();
        transaction.setCusip(cusip);
        transaction.setUsername(username);
        transaction.setTransactionamount(bidAmount);
        transaction.setTransactiontime(currentTime);
        transaction.setPaymentmethod("Paypal");
        transaction.setTransactiontype("buy");
        transactionMapper.insert(transaction);
        return ResponseResult.okResult();
    }

    public ResponseResult update(String cusip, String username, Double bidAmount) {
        // 对数据进行非空判断
        if (!StringUtils.hasText(cusip)) {
            throw new SystemException(AppHttpCodeEnum.CUSIP_NOT_NULL);
        }
        if (!StringUtils.hasText(username)) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        if (bidAmount == null) {
            throw new SystemException(AppHttpCodeEnum.BidAmount_NOT_NULL);
        }

        // 对数据进行存在判断
        if (!bondService.bondExist(cusip)) {
            throw new SystemException(AppHttpCodeEnum.BOND_NOT_EXISTS);
        }
        if (!userService.userNameExist(username)) {
            throw new SystemException(AppHttpCodeEnum.USER_NOT_EXISTS);
        }

        // 向数据库中添加数据
        Bid bid = new Bid();
        bid.setCusip(cusip);
        bid.setUsername(username);
        bid.setBidvalue(bidAmount);
        // 获取当前时间并设置给bidtime字段
        Date currentTime = new Date();
        bid.setBidtime(currentTime);

        UpdateWrapper<Bid> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("cusip", bid.getCusip())
                     .eq("username", bid.getUsername()); // 设置更新条件
        bidMapper.update(bid, updateWrapper);

        Transaction transaction = new Transaction();
        transaction.setCusip(cusip);
        transaction.setUsername(username);
        transaction.setTransactionamount(bidAmount);
        transaction.setTransactiontime(currentTime);
        transaction.setPaymentmethod("Paypal");
        transaction.setTransactiontype("buy");

        UpdateWrapper<Transaction> updateWrapper1 = new UpdateWrapper<>();
        updateWrapper1.eq("cusip", bid.getCusip())
                      .eq("username", bid.getUsername()); // 设置更新条件
        transactionMapper.update(transaction, updateWrapper1);

        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult delete(String cusip, String username) {
        // 查询数据库中信息
        LambdaQueryWrapper<Bid> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Bid::getCusip, cusip);
        queryWrapper.eq(Bid::getUsername, username);
        Bid bid = bidMapper.selectOne(queryWrapper);

        LambdaQueryWrapper<Transaction> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(Transaction::getCusip, cusip);
        queryWrapper1.eq(Transaction::getUsername, username);

        // 判断是否存在符合要求的bid
        if (bid == null) {
            throw new SystemException(AppHttpCodeEnum.DELETE_BOND_NOT_FOUND);
        } else {
            // 执行数据库删除操作
            int rows = bidMapper.delete(queryWrapper);
            transactionMapper.delete(queryWrapper1);
            if (rows > 0) {
                return ResponseResult.okResult();
            } else {
                // 删除失败处理
                throw new SystemException(AppHttpCodeEnum.DELETE_BID_FAILED);
            }
        }
    }

    @Override
    public ResponseResult details(String cusip) {
        // 1. 根据cusip查询债券bond信息
        LambdaQueryWrapper<Bond> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Bond::getCusip, cusip);
        Bond bond = bondMapper.selectOne(queryWrapper);

        // 封装成ProductInfoVo对象
        ProductInfoVo productInfoVo = BeanCopyUtils.copyBean(bond, ProductInfoVo.class);

        // 2. 根据cusip查询所有持有该债券的用户信息
        LambdaQueryWrapper<Bid> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Bid::getCusip, cusip);
        wrapper.orderByDesc(Bid::getBidvalue);
        List<Bid> bids = bidMapper.selectList(wrapper);

        // 封装成UserBidsVo对象
        List<UserBidsVo> userBidsVos = BeanCopyUtils.copyBeanList(bids, UserBidsVo.class);
        // 给UserBidsVo对象中的ranking字段赋值
        userBidsVos.stream()
                .forEach(userBidsVo -> userBidsVo.setRanking((userBidsVos.indexOf(userBidsVo) + 1) + "/" + userBidsVos.size()));

        // 3. 封装返回
        BidDetailsVo bidDetailsVo = new BidDetailsVo(productInfoVo, userBidsVos);
        return ResponseResult.okResult(bidDetailsVo);
    }
}
