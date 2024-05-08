package com.greentechpay.bff.mapper;

import com.greentechpay.bff.dto.request.RequestPaymentHistoryDto;
import com.greentechpay.bff.dto.response.ResponsePaymentHistoryDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-08T14:45:40+0400",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class PaymentHistoryMapperImpl implements PaymentHistoryMapper {

    @Override
    public ResponsePaymentHistoryDto requestToResponse(RequestPaymentHistoryDto requestPaymentHistoryDto) {
        if ( requestPaymentHistoryDto == null ) {
            return null;
        }

        ResponsePaymentHistoryDto responsePaymentHistoryDto = new ResponsePaymentHistoryDto();

        responsePaymentHistoryDto.setId( requestPaymentHistoryDto.getId() );
        responsePaymentHistoryDto.setSenderRequestId( requestPaymentHistoryDto.getSenderRequestId() );
        responsePaymentHistoryDto.setAmount( requestPaymentHistoryDto.getAmount() );
        responsePaymentHistoryDto.setToUser( requestPaymentHistoryDto.getToUser() );
        responsePaymentHistoryDto.setServiceName( requestPaymentHistoryDto.getServiceName() );
        responsePaymentHistoryDto.setTransferType( requestPaymentHistoryDto.getTransferType() );
        responsePaymentHistoryDto.setPaymentDate( requestPaymentHistoryDto.getPaymentDate() );
        responsePaymentHistoryDto.setStatus( requestPaymentHistoryDto.getStatus() );

        return responsePaymentHistoryDto;
    }
}
