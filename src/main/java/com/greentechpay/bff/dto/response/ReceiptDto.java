package com.greentechpay.bff.dto.response;


import com.greentechpay.bff.dto.Status;
import com.greentechpay.bff.dto.TransferType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
public class ReceiptDto {
    private BigDecimal amount;
    private Timestamp paymentDate;
    private String senderRequestId;
    private String name;
    private String from;
    private String to;
    private String currency;
    private TransferType type;
    private Status status;
}
