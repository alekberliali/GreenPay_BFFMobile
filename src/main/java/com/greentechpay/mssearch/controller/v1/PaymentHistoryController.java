package com.greentechpay.mssearch.controller.v1;

import com.greentechpay.mssearch.dto.request.PageRequestDto;
import com.greentechpay.mssearch.dto.request.StatisticDto;
import com.greentechpay.mssearch.dto.response.PageResponse;
import com.greentechpay.mssearch.dto.ReceiptHistoryDto;
import com.greentechpay.mssearch.dto.response.ResponseReceiptDto;
import com.greentechpay.mssearch.service.PaymentHistoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payment-history")
public class PaymentHistoryController {

    private final PaymentHistoryService paymentHistoryService;

    @PostMapping("/page/{id}")
    public ResponseEntity<PageResponse<Date, List<ReceiptHistoryDto>>> getAllWithPageByUserId(@PathVariable String id, @Valid @RequestBody PageRequestDto pageRequestDto) {
        return ResponseEntity.ok(paymentHistoryService.getAllByPage(id, pageRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseReceiptDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(paymentHistoryService.getById(id));
    }

    @PostMapping("/statistics")
    public ResponseEntity<Map<String, BigDecimal>> getStatisticsByUserId(@Valid @RequestBody StatisticDto statisticDto) {
        return ResponseEntity.ok(paymentHistoryService.getStatisticsByUserId(statisticDto));
    }
}
