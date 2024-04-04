package com.greentechpay.mssearch.mapper;

import com.greentechpay.mssearch.dto.response.ServiceDto;
import com.greentechpay.mssearch.entity.AppService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    ServiceDto entityToDto(AppService service);
}
