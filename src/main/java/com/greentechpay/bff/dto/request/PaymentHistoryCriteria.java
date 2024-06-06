package com.greentechpay.bff.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class PaymentHistoryCriteria {
    private String userId;
    private LocalDate startDate;
    private LocalDate endDate;
}
