package com.bondsales.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bondsales.ResponseResult;
import com.bondsales.entity.Transaction;
import com.bondsales.mapper.TransactionMapper;
import com.bondsales.service.TransactionService;
import com.bondsales.vo.TransactionVo;
import com.bondsales.vo.UserNameVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Transaction)表服务实现类
 *
 * @author makejava
 * @since 2024-04-10 22:13:07
 */
@Service("transactionService")
public class TransactionServiceImpl extends ServiceImpl<TransactionMapper, Transaction> implements TransactionService {
    @Override
    public ResponseResult getTransactionHistory(UserNameVo userNameVo) {
        String username = userNameVo.getUsername();
        List<TransactionVo> history = baseMapper.findTransactionHistoryByUsername(username);
        //前端代码中已经处理了history为空的情况，所以这里不需要再次处理
//        if (history.isEmpty()) {
//            return new ResponseResult(-110, "Transaction history not found", null);
//        }
        return new ResponseResult(0, "Success", history);
    }
}
