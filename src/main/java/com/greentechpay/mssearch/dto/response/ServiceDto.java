package com.greentechpay.mssearch.dto.response;

public record ServiceDto(Integer id, String name, Integer minAmount, Integer maxAmount, String currency) {
}
