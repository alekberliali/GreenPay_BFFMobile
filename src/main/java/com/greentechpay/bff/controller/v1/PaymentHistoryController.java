package com.greentechpay.bff.controller.v1;

import com.greentechpay.bff.dto.response.PageResponse;
import com.greentechpay.bff.dto.response.ReceiptDto;
import com.greentechpay.bff.dto.response.PaymentHistoryDto;
import com.greentechpay.bff.dto.response.ResponseDto;
import com.greentechpay.bff.service.PaymentHistoryService;
import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<PageResponse<Map<LocalDate, List<PaymentHistoryDto>>>>
    getAllWithPageByUserId(@RequestHeader(value = "agent-name") String agentName,
                           @RequestHeader(value = "agent-password") String agentPassword,
                           @RequestHeader(value = "agent-id") String agentId,
                           @RequestHeader(value = "access-token") String accessToken,
                           @RequestParam @Min(value = 0, message = "pages size can not be less than 0") Integer page,
                           @RequestParam @Min(value = 0, message = "elements size can not be less than 0") Integer size,
                           @RequestParam @NotBlank(message = "user id can not be null") String userId,
                           @RequestParam @Nullable String iban,
                           @RequestParam @Nullable @Past(message = "date must be in past") LocalDate startDate,
                           @RequestParam @Nullable @Past(message = "date must be in past") LocalDate endDate) {
        return ResponseEntity.ok(paymentHistoryService.getAllByPage(agentName, agentPassword, agentId, accessToken,
                page, size, userId, iban, startDate, endDate));
    }

   /* @GetMapping("/sender-request-id/{id}")
    public ResponseEntity<ReceiptDto> getSenderRequestId(@PathVariable String id) {
        return ResponseEntity.ok(paymentHistoryService.getBySenderRequestId(id));
    }*/

    @GetMapping("/receipt/{id}")
    public ResponseEntity<ReceiptDto> getById(@RequestHeader(value = "agent-name") String agentName,
                                              @RequestHeader(value = "agent-password") String agentPassword,
                                              @RequestHeader(value = "agent-id") String agentId,
                                              @RequestHeader(value = "access-token") String accessToken,
                                              @PathVariable Long id) {
        return ResponseEntity.ok(paymentHistoryService.getById(agentName, agentPassword, agentId, accessToken, id));
    }

    @GetMapping("/statistics")
    public ResponseEntity<ResponseDto<Map<String, BigDecimal>>>
    getStatisticsByUserId(@RequestParam @NotBlank(message = "iban can not be empty") String iban,
                          @RequestParam @Nullable @Past(message = "date must be a past date") LocalDate startDate,
                          @RequestParam @Nullable @Past(message = "date must be a past date") LocalDate endDate) {
        return ResponseEntity.ok(paymentHistoryService.getStatisticsByUserId(iban, startDate, endDate));
    }
}
