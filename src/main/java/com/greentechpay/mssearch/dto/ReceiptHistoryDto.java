package com.greentechpay.mssearch.dto;

import com.greentechpay.mssearch.entity.PaymentStatus;
import com.greentechpay.mssearch.entity.TransferType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data

public class ReceiptHistoryDto {
    private String id;
    private BigDecimal amount;
    private String toUser;
    private String serviceName;
    private TransferType transferType;
    private Timestamp paymentDate;
    private PaymentStatus paymentStatus;
}
