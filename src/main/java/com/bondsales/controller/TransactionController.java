package com.bondsales.controller;

import com.bondsales.ResponseResult;
import com.bondsales.service.TransactionService;
import com.bondsales.vo.UserNameVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/history")
    public ResponseResult getTransactionHistory(@RequestBody UserNameVo userNameVo) {
        return transactionService.getTransactionHistory(userNameVo);
    }
}
