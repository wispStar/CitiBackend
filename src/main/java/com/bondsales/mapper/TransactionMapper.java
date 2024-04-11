package com.bondsales.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bondsales.entity.Transaction;
import com.bondsales.vo.TransactionVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * (Transaction)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-10 22:13:07
 */
public interface TransactionMapper extends BaseMapper<Transaction> {
    List<TransactionVo> findTransactionHistoryByUsername(@Param("username") String username);
}
