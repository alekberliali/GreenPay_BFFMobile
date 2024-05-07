package com.greentechpay.bff.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ResponsePaymentHistoryDto {
    private Long id;
    private String senderRequestId;
    private BigDecimal amount;
    private String toUser;
    private String serviceName;
    private String transferType;
    private LocalDateTime paymentDate;
    private String status;
}
