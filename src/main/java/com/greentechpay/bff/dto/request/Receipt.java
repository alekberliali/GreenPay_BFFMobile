package com.greentechpay.bff.dto.request;

import com.greentechpay.bff.dto.Currency;
import com.greentechpay.bff.dto.Status;
import com.greentechpay.bff.dto.TransferType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class Receipt {
    private BigDecimal amount;
    private LocalDateTime paymentDate;
    private String senderRequestId;
    private Integer serviceId;
    private String from;
    private String to;
    private String field;
    private Currency currency;
    private TransferType type;
    private Status status;
}
