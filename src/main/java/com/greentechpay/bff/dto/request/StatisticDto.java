package com.greentechpay.bff.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record StatisticDto(@NotEmpty(message = "userId can not be empty") String userId,
                           @NotNull @Past(message = "startDate must be in the past") LocalDate startDate,
                           @NotNull @Past(message = "endDate must be in the past") LocalDate endDate) {
}
