package com.greentechpay.bff.client;

import com.greentechpay.bff.client.response.PaymentHistory;
import com.greentechpay.bff.dto.request.*;
import com.greentechpay.bff.dto.response.PageResponse;
import com.greentechpay.bff.dto.response.ReceiptDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@FeignClient(value = "history", url = "${app.feign.history.config.url}")
public interface PaymentHistoryClient {

    @PostMapping(value = "/payment-history/filter")
    PageResponse<List<PaymentHistory>> getUserHistoryByUserId(FilterDto<PaymentHistoryCriteria> filterDto);

    @GetMapping("/payment-history/{id}")
    PaymentHistory getById(@PathVariable Long id, @RequestParam @Nullable Long merchantId);

    @PostMapping("/payment-history/category-statistics")
    ResponseEntity<Map<String, BigDecimal>> getStatisticsByUserId(StatisticDto statisticDto);
}
