package com.greentechpay.bff.mapper;

import com.greentechpay.bff.client.response.PaymentHistory;
import com.greentechpay.bff.dto.response.PaymentHistoryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentHistoryMapper {
    PaymentHistoryDto requestToResponse(PaymentHistory paymentHistory);
}
