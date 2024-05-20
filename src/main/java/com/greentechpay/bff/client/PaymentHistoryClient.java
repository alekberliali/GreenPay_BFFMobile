package com.greentechpay.bff.client;

import com.greentechpay.bff.dto.request.RequestPaymentHistoryDto;
import com.greentechpay.bff.dto.request.PageRequestDto;
import com.greentechpay.bff.dto.request.StatisticDto;
import com.greentechpay.bff.dto.response.PageResponse;
import com.greentechpay.bff.dto.response.ReceiptDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@FeignClient(value = "history", url = "${app.feign.history.config.url}")
public interface PaymentHistoryClient {

    @PostMapping(value = "/page/{id}")
    ResponseEntity<PageResponse<LocalDate, List<RequestPaymentHistoryDto>>>
    getUserHistoryByUserId(@PathVariable String id, PageRequestDto pageRequestDto);

    @GetMapping(value = "/sender-request-id/{senderRequestId}")
    ResponseEntity<ReceiptDto> getBySenderRequestId(@PathVariable String senderRequestId);

    @GetMapping("/receipt-id/{id}")
    ResponseEntity<ReceiptDto> getById(@PathVariable Long id);

    @PostMapping("/statistics")
    ResponseEntity<Map<String, BigDecimal>> getStatisticsByUserId(StatisticDto statisticDto);
}
