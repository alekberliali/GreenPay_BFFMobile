package com.greentechpay.mssearch.dto.response;

import java.util.List;

public record ResponseCategoryDto(String name, List<ServiceDto> serviceDtoList) {
}
