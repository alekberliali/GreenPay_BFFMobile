package com.greentechpay.bff.dto.request;


import com.greentechpay.bff.dto.Status;
import com.greentechpay.bff.dto.TransferType;
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
    private TransferType transferType;
    private LocalDateTime paymentDate;
    private Status status;
}
