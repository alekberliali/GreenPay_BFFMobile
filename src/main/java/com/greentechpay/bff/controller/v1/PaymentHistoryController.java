package com.greentechpay.bff.controller.v1;

import com.greentechpay.bff.dto.response.PageResponse;
import com.greentechpay.bff.dto.response.ReceiptDto;
import com.greentechpay.bff.dto.response.ResponsePaymentHistoryDto;
import com.greentechpay.bff.service.PaymentHistoryService;
import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payment-history")
@Validated
public class PaymentHistoryController {

    private final PaymentHistoryService paymentHistoryService;

    @GetMapping("/page")
    public ResponseEntity<PageResponse<Map<LocalDate, List<ResponsePaymentHistoryDto>>>>
    getAllWithPageByUserId(@RequestParam @Min(value = 0, message = "pages size can not be less than 0") Integer page,
                           @RequestParam @Min(value = 0, message = "elements size can not be less than 0") Integer size,
                           @RequestParam @NotBlank(message = "user id can not be empty") String userId,
                           @RequestParam @Nullable @Past(message = "date must be in past") LocalDate startDate,
                           @RequestParam @Nullable @Past(message = "date must be in past") LocalDate endDate) {
        return ResponseEntity.ok(paymentHistoryService.getAllByPage(page, size, userId, startDate, endDate));
    }

    @GetMapping("/sender-request-id/{id}")
    public ResponseEntity<ReceiptDto> getSenderRequestId(@PathVariable String id) {
        return ResponseEntity.ok(paymentHistoryService.getBySenderRequestId(id));
    }

    @GetMapping("/receipt/{id}")
    public ResponseEntity<ReceiptDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentHistoryService.getById(id));
    }

    @GetMapping("/statistics")
    public ResponseEntity<Map<String, BigDecimal>>
    getStatisticsByUserId(@RequestParam @NotBlank(message = "user id can not be empty") String userId,
                          @RequestParam @Nullable @Past(message = "date must be a past date") LocalDate startDate,
                          @RequestParam @Nullable @Past(message = "date must be a past date") LocalDate endDate) {
        return ResponseEntity.ok(paymentHistoryService.getStatisticsByUserId(userId, startDate, endDate));
    }
}
