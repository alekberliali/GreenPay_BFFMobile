package com.greentechpay.mssearch.mapper;

import com.greentechpay.mssearch.dto.response.ResponseCategoryDto;
import com.greentechpay.mssearch.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(source = "appServiceList", target = "serviceDtoList")
    ResponseCategoryDto entityToDto(Category category);

}
