package com.greentechpay.bff.service;

import com.greentechpay.bff.client.AuthClient;
import com.greentechpay.bff.client.PaymentHistoryClient;
import com.greentechpay.bff.dto.request.RequestPaymentHistoryDto;
import com.greentechpay.bff.dto.request.PageRequestDto;
import com.greentechpay.bff.dto.request.StatisticDto;
import com.greentechpay.bff.dto.response.PageResponse;
import com.greentechpay.bff.dto.response.ReceiptDto;
import com.greentechpay.bff.dto.response.ResponsePaymentHistoryDto;
import com.greentechpay.bff.mapper.PaymentHistoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class PaymentHistoryService {

    private final PaymentHistoryClient paymentHistoryClient;
    private final PaymentHistoryMapper paymentHistoryMapper;
    private final AuthClient authClient;


    public PageResponse<LocalDate, List<ResponsePaymentHistoryDto>>
    getAllByPage(String userId, Integer page, Integer size) {
        PageRequestDto pageRequestDto = new PageRequestDto(page, size);
        Map<LocalDate, List<ResponsePaymentHistoryDto>> map = new HashMap<>();

        var response = Objects.requireNonNull(paymentHistoryClient.getUserHistoryByUserId(userId, pageRequestDto).getBody());
        var dates = response.getContent().keySet();
        var results = response.getContent();
        var pages = response.getTotalPages();
        var elements = response.getTotalElements();

        for (LocalDate a : dates) {
            List<ResponsePaymentHistoryDto> list = new ArrayList<>();
            for (RequestPaymentHistoryDto historyDto : results.get(a)) {
                list.add(paymentHistoryMapper.requestToResponse(historyDto));
            }
            map.put(a, list);
        }
        return PageResponse.<LocalDate, List<ResponsePaymentHistoryDto>>builder()
                .totalPages(pages)
                .totalElements(elements)
                .content(map)
                .build();
    }

    public ReceiptDto getBySenderRequestId(String senderRequestId) {
        var receipt = paymentHistoryClient.getBySenderRequestId(senderRequestId).getBody();
        String phoneNumber = authClient.getNumberById(Objects.requireNonNull(receipt).getFrom());
        receipt.setFrom(phoneNumber);
        return receipt;
    }

    public Map<String, BigDecimal> getStatisticsByUserId(String userId, LocalDate startDate, LocalDate endDate) {
        StatisticDto statisticDto = new StatisticDto(userId, startDate, endDate);
        return paymentHistoryClient.getStatisticsByUserId(statisticDto).getBody();
    }

    public ReceiptDto getById(Long id) {
        return paymentHistoryClient.getById(id).getBody();
    }
}