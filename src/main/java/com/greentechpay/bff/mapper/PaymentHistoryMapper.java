package com.greentechpay.bff.mapper;

import com.greentechpay.bff.dto.request.RequestPaymentHistoryDto;
import com.greentechpay.bff.dto.response.ResponsePaymentHistoryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentHistoryMapper {
    ResponsePaymentHistoryDto requestToResponse(RequestPaymentHistoryDto requestPaymentHistoryDto);
}
