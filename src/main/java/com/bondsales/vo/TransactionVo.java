package com.bondsales.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionVo {
    private String username;
    private String cusip;
    private LocalDateTime transactionTime;
    private String transactionType;
    private String productName;
    private Integer quantity;
    private BigDecimal transactionamount;
}
