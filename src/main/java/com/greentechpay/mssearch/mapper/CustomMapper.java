package com.greentechpay.mssearch.mapper;

import com.greentechpay.mssearch.dto.ReceiptHistoryDto;
import com.greentechpay.mssearch.entity.PaymentHistory;
import com.greentechpay.mssearch.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomMapper {
    private final ServiceService serviceService;

    //TODO id will be transactionId
    public ReceiptHistoryDto converter(PaymentHistory paymentHistory) {
        ReceiptHistoryDto receiptHistoryDto = new ReceiptHistoryDto();
        receiptHistoryDto.setId(paymentHistory.getId());
        receiptHistoryDto.setAmount(paymentHistory.getAmount());
        receiptHistoryDto.setToUser(paymentHistory.getToUser());
        if (paymentHistory.getServiceId()!=0) {
            receiptHistoryDto.setServiceName(serviceService.getById(paymentHistory.getServiceId()).getName());
        }
        receiptHistoryDto.setPaymentStatus(paymentHistory.getPaymentStatus());
        receiptHistoryDto.setTransferType(paymentHistory.getTransferType());
        receiptHistoryDto.setPaymentDate(paymentHistory.getPaymentDate());
        return receiptHistoryDto;
    }
}
