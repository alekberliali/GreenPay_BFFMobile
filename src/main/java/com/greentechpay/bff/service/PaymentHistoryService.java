package com.greentechpay.bff.service;

import com.greentechpay.bff.client.AuthClient;
import com.greentechpay.bff.client.PaymentHistoryClient;
import com.greentechpay.bff.client.ServiceClient;
import com.greentechpay.bff.dto.TransferType;
import com.greentechpay.bff.dto.request.*;
import com.greentechpay.bff.dto.response.PageResponse;
import com.greentechpay.bff.dto.response.ReceiptDto;
import com.greentechpay.bff.dto.response.ResponsePaymentHistoryDto;
import com.greentechpay.bff.mapper.PaymentHistoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PaymentHistoryService {

    private final PaymentHistoryClient paymentHistoryClient;
    private final PaymentHistoryMapper paymentHistoryMapper;
    private final AuthClient authClient;
    private final ServiceClient serviceClient;

    public PageResponse<Map<LocalDate, List<ResponsePaymentHistoryDto>>>
    getAllByPage(Integer page, Integer size, String userId, LocalDate startDate, LocalDate endDate) {

        PageRequestDto pageRequestDto = new PageRequestDto(page, size);
        PaymentHistoryCriteria paymentHistoryCriteria = new PaymentHistoryCriteria(userId, startDate, endDate);
        FilterDto<PaymentHistoryCriteria> filterDto = new FilterDto<>(pageRequestDto, paymentHistoryCriteria);

        var response = Objects.requireNonNull(paymentHistoryClient.getUserHistoryByUserId(filterDto).getBody());
        var results = response.getContent();
        var pages = response.getTotalPages();
        var elements = response.getTotalElements();

        Map<LocalDate, List<ResponsePaymentHistoryDto>> map = new HashMap<>();
        for (RequestPaymentHistoryDto dto : results) {
            LocalDate date = dto.getPaymentDate().toLocalDate();
            List<ResponsePaymentHistoryDto> list = map.getOrDefault(date, new ArrayList<>());
            var responseDto = paymentHistoryMapper.requestToResponse(dto);
            responseDto.setServiceName(serviceClient.getNameById(dto.getServiceId()));
            list.add(responseDto);
            map.put(date, list);
        }

        Map<LocalDate, List<ResponsePaymentHistoryDto>> sortedMap = new TreeMap<>(Collections.reverseOrder());
        sortedMap.putAll(map);

        return PageResponse.<Map<LocalDate, List<ResponsePaymentHistoryDto>>>builder()
                .totalPages(pages)
                .totalElements(elements)
                .content(sortedMap)
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
        var request = paymentHistoryClient.getById(id).getBody();
        assert request != null;
        if (request.getType().equals(TransferType.Payment)) {
            return ReceiptDto.builder()
                    .amount(request.getAmount())
                    .serviceName(serviceClient.getNameById(request.getServiceId()))
                    .senderRequestId(request.getSenderRequestId())
                    .from(request.getFrom())
                    .field(request.getField())
                    .paymentDate(request.getPaymentDate())
                    .currency(request.getCurrency())
                    .type(request.getType())
                    .status(request.getStatus())
                    .build();

        } else {
            return ReceiptDto.builder()
                    .amount(request.getAmount())
                    .senderRequestId(request.getSenderRequestId())
                    .from(request.getFrom())
                    .to(authClient.getNumberById(request.getTo()))
                    .field(request.getField())
                    .paymentDate(request.getPaymentDate())
                    .currency(request.getCurrency())
                    .type(request.getType())
                    .status(request.getStatus())
                    .build();
        }
    }
}