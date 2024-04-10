package com.bondsales.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bondsales.entity.Transaction;
import com.bondsales.mapper.TransactionMapper;
import com.bondsales.service.TransactionService;
import org.springframework.stereotype.Service;

/**
 * (Transaction)表服务实现类
 *
 * @author makejava
 * @since 2024-04-10 22:13:07
 */
@Service("transactionService")
public class TransactionServiceImpl extends ServiceImpl<TransactionMapper, Transaction> implements TransactionService {

}
