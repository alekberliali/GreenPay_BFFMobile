package com.greentechpay.mssearch.dto.response;

import com.greentechpay.mssearch.entity.PaymentStatus;
import com.greentechpay.mssearch.entity.TransferType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
public class ResponseReceiptDto {
    private BigDecimal amount;
    private Timestamp paymentDate;
    private String senderRequestId;
    private String name;
    private String from;
    private String to;
    private String currency;
    private TransferType type;
    private PaymentStatus status;
}
