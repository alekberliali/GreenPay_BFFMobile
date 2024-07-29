package com.greentechpay.bff.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.greentechpay.bff.dto.Status;
import com.greentechpay.bff.dto.TransferType;
import com.greentechpay.bff.dto.Currency;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReceiptDto {
    private String receiptId;
    private BigDecimal amount;
    private LocalDateTime paymentDate;
    private String senderRequestId;
    private String serviceName;
    private String categoryName;
    private String from;
    private String to;
    private String field;
    private Currency currency;
    private TransferType type;
    private Status status;
}
