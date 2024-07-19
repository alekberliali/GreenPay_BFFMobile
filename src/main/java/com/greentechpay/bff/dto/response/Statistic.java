package com.greentechpay.bff.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Statistic {
    private String name;
    private BigDecimal amount;
    private BigDecimal percentage;
}
