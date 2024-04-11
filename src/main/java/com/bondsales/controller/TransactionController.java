package com.bondsales.controller;

import com.bondsales.ResponseResult;
import com.bondsales.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/history")
    public ResponseResult getTransactionHistory(@RequestParam String username) {
        return transactionService.getTransactionHistory(username);
    }
}
