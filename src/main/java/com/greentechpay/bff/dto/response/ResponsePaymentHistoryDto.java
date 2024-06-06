package com.greentechpay.bff.dto.response;

import com.greentechpay.bff.dto.Status;
import com.greentechpay.bff.dto.TransferType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ResponsePaymentHistoryDto {
    private Long id;
    private String senderRequestId;
    private BigDecimal amount;
    private String requestField;
    private String serviceName;
    private TransferType transferType;
    private LocalDateTime paymentDate;
    private Status status;
}
