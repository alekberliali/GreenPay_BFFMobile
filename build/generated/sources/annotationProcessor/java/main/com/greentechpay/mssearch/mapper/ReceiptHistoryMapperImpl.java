package com.greentechpay.mssearch.mapper;

import com.greentechpay.mssearch.dto.ReceiptHistoryDto;
import com.greentechpay.mssearch.entity.PaymentHistory;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-07T19:12:27+0400",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class ReceiptHistoryMapperImpl implements ReceiptHistoryMapper {

    @Override
    public ReceiptHistoryDto entityToDto(PaymentHistory paymentHistory) {
        if ( paymentHistory == null ) {
            return null;
        }

        ReceiptHistoryDto receiptHistoryDto = new ReceiptHistoryDto();

        receiptHistoryDto.setId( paymentHistory.getId() );
        receiptHistoryDto.setAmount( paymentHistory.getAmount() );
        receiptHistoryDto.setToUser( paymentHistory.getToUser() );
        receiptHistoryDto.setTransferType( paymentHistory.getTransferType() );
        receiptHistoryDto.setPaymentDate( paymentHistory.getPaymentDate() );
        receiptHistoryDto.setPaymentStatus( paymentHistory.getPaymentStatus() );

        return receiptHistoryDto;
    }
}
