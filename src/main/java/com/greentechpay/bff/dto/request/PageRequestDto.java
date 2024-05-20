package com.greentechpay.bff.dto.request;

import jakarta.validation.constraints.NotNull;

public record PageRequestDto(Integer page, Integer size) {
}
