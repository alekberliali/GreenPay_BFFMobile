package com.greentechpay.bff.mapper;

import com.greentechpay.bff.client.response.PaymentHistory;
import com.greentechpay.bff.dto.response.PaymentHistoryDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-02T17:32:27+0400",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class PaymentHistoryMapperImpl implements PaymentHistoryMapper {

    @Override
    public PaymentHistoryDto requestToResponse(PaymentHistory paymentHistory) {
        if ( paymentHistory == null ) {
            return null;
        }

        PaymentHistoryDto paymentHistoryDto = new PaymentHistoryDto();

        paymentHistoryDto.setId( paymentHistory.getId() );
        paymentHistoryDto.setSenderRequestId( paymentHistory.getSenderRequestId() );
        paymentHistoryDto.setAmount( paymentHistory.getAmount() );
        paymentHistoryDto.setRequestField( paymentHistory.getRequestField() );
        paymentHistoryDto.setCategoryName( paymentHistory.getCategoryName() );
        paymentHistoryDto.setTransferType( paymentHistory.getTransferType() );
        paymentHistoryDto.setPaymentDate( paymentHistory.getPaymentDate() );
        paymentHistoryDto.setStatus( paymentHistory.getStatus() );

        return paymentHistoryDto;
    }
}
