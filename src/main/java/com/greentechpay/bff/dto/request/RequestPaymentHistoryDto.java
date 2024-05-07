package com.greentechpay.bff.dto.request;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class RequestPaymentHistoryDto {
    private Long id;
    private String senderRequestId;
    private BigDecimal amount;
    private String toUser;
    private String serviceName;
    private String transferType;
    private LocalDateTime paymentDate;
    private String status;
}
