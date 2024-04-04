package com.greentechpay.mssearch.dto.request;

import jakarta.validation.constraints.NotNull;

public record PageRequestDto(@NotNull(message = "page can not be null") Integer page,
                             @NotNull(message = "size can not be null") Integer size) {
}
