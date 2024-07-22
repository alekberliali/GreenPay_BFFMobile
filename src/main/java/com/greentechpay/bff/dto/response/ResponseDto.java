package com.greentechpay.bff.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDto<T> {
    private Double totalAmount;
    private T data;
}
