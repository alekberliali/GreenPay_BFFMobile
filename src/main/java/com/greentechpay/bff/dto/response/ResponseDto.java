package com.greentechpay.bff.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ResponseDto<T> {
    private BigDecimal totalAmount;
    private T data;
}
