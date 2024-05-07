package com.greentechpay.bff.controller.v1;

import com.greentechpay.bff.dto.request.PageRequestDto;
import com.greentechpay.bff.dto.response.PageResponse;
import com.greentechpay.bff.dto.response.ReceiptDto;
import com.greentechpay.bff.dto.response.ResponsePaymentHistoryDto;
import com.greentechpay.bff.service.PaymentHistoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payment-history")
public class PaymentHistoryController {

    private final PaymentHistoryService paymentHistoryService;

    @PostMapping("/page/{id}")
    public ResponseEntity<PageResponse<Date, List<ResponsePaymentHistoryDto>>> getAllWithPageByUserId(@PathVariable String id, @Valid @RequestBody PageRequestDto pageRequestDto) {
        return ResponseEntity.ok(paymentHistoryService.getAllByPage(id, pageRequestDto));
    }

   @GetMapping("/sender-request-id/{id}")
    public ResponseEntity<ReceiptDto> getSenderRequestId(@PathVariable String id) {
        return ResponseEntity.ok(paymentHistoryService.getBySenderRequestId(id));
    }

    @GetMapping("/receipt-id/{id}")
    public ResponseEntity<ReceiptDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentHistoryService.getById(id));
    }
/*
    @PostMapping("/statistics")
    public ResponseEntity<Map<String, BigDecimal>> getStatisticsByUserId(@Valid @RequestBody StatisticDto statisticDto) {
        return ResponseEntity.ok(paymentHistoryService.getStatisticsByUserId(statisticDto));
    }*/
}
