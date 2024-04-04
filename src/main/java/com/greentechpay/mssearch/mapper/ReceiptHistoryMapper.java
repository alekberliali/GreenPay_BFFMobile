package com.greentechpay.mssearch.mapper;

import com.greentechpay.mssearch.dto.ReceiptHistoryDto;
import com.greentechpay.mssearch.entity.AppService;
import com.greentechpay.mssearch.entity.PaymentHistory;
import com.greentechpay.mssearch.repository.ServiceRepository;
import com.greentechpay.mssearch.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public interface ReceiptHistoryMapper {

    ReceiptHistoryDto entityToDto(PaymentHistory paymentHistory);
}
