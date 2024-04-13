package com.bondsales.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bondsales.ResponseResult;
import com.bondsales.entity.Transaction;
import com.bondsales.vo.UserNameVo;


/**
 * (Transaction)表服务接口
 *
 * @author makejava
 * @since 2024-04-10 22:13:07
 */
public interface TransactionService extends IService<Transaction> {
    ResponseResult getTransactionHistory(UserNameVo userNameVo);
}
