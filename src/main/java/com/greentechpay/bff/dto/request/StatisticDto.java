package com.greentechpay.bff.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record StatisticDto(String iban, LocalDate startDate, LocalDate endDate) {
}
