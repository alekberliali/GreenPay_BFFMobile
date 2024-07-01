package com.greentechpay.bff.mapper;

import com.greentechpay.bff.client.response.PaymentHistory;
import com.greentechpay.bff.dto.response.PaymentHistoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentHistoryMapper {
    @Mapping(target = "serviceName", ignore = true)
    PaymentHistoryDto requestToResponse(PaymentHistory paymentHistory);
}
