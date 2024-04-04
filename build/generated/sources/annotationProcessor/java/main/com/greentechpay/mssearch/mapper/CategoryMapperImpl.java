package com.greentechpay.mssearch.mapper;

import com.greentechpay.mssearch.dto.response.ResponseCategoryDto;
import com.greentechpay.mssearch.dto.response.ServiceDto;
import com.greentechpay.mssearch.entity.AppService;
import com.greentechpay.mssearch.entity.Category;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-07T19:12:27+0400",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public ResponseCategoryDto entityToDto(Category category) {
        if ( category == null ) {
            return null;
        }

        List<ServiceDto> serviceDtoList = null;
        String name = null;

        serviceDtoList = appServiceListToServiceDtoList( category.getAppServiceList() );
        name = category.getName();

        ResponseCategoryDto responseCategoryDto = new ResponseCategoryDto( name, serviceDtoList );

        return responseCategoryDto;
    }

    protected ServiceDto appServiceToServiceDto(AppService appService) {
        if ( appService == null ) {
            return null;
        }

        Integer id = null;
        String name = null;
        Integer minAmount = null;
        Integer maxAmount = null;
        String currency = null;

        id = appService.getId();
        name = appService.getName();
        minAmount = appService.getMinAmount();
        maxAmount = appService.getMaxAmount();
        currency = appService.getCurrency();

        ServiceDto serviceDto = new ServiceDto( id, name, minAmount, maxAmount, currency );

        return serviceDto;
    }

    protected List<ServiceDto> appServiceListToServiceDtoList(List<AppService> list) {
        if ( list == null ) {
            return null;
        }

        List<ServiceDto> list1 = new ArrayList<ServiceDto>( list.size() );
        for ( AppService appService : list ) {
            list1.add( appServiceToServiceDto( appService ) );
        }

        return list1;
    }
}
