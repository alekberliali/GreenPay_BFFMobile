package com.greentechpay.mssearch.mapper;

import com.greentechpay.mssearch.dto.response.ServiceDto;
import com.greentechpay.mssearch.entity.AppService;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-07T19:12:27+0400",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class ServiceMapperImpl implements ServiceMapper {

    @Override
    public ServiceDto entityToDto(AppService service) {
        if ( service == null ) {
            return null;
        }

        Integer id = null;
        String name = null;
        Integer minAmount = null;
        Integer maxAmount = null;
        String currency = null;

        id = service.getId();
        name = service.getName();
        minAmount = service.getMinAmount();
        maxAmount = service.getMaxAmount();
        currency = service.getCurrency();

        ServiceDto serviceDto = new ServiceDto( id, name, minAmount, maxAmount, currency );

        return serviceDto;
    }
}
