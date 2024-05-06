package com.greentechpay.bff.client;

import com.greentechpay.bff.dto.request.RequestPaymentHistoryDto;
import com.greentechpay.bff.dto.request.PageRequestDto;
import com.greentechpay.bff.dto.response.PageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Date;
import java.util.List;

@FeignClient(value = "history", url = "${app.feign.history.config.url}")
public interface PaymentHistoryClient {

    @PostMapping(value = "/page/{id}")
    ResponseEntity<PageResponse<Date, List<RequestPaymentHistoryDto>>> getUserHistoryByUserId(@PathVariable String id, PageRequestDto pageRequestDto);
}
