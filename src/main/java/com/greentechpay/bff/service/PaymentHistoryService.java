package com.greentechpay.bff.service;


import com.greentechpay.bff.client.PaymentHistoryClient;
import com.greentechpay.bff.dto.request.RequestPaymentHistoryDto;
import com.greentechpay.bff.dto.request.PageRequestDto;
import com.greentechpay.bff.dto.response.PageResponse;
import com.greentechpay.bff.dto.response.ReceiptDto;
import com.greentechpay.bff.dto.response.ResponsePaymentHistoryDto;
import com.greentechpay.bff.mapper.PaymentHistoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PaymentHistoryService {

    private final PaymentHistoryClient paymentHistoryClient;
    private final PaymentHistoryMapper paymentHistoryMapper;


    public PageResponse<Date, List<ResponsePaymentHistoryDto>> getAllByPage(String userId, PageRequestDto pageRequestDto) {

        Map<Date, List<ResponsePaymentHistoryDto>> map = new HashMap<>();
        var dates = Objects.requireNonNull(paymentHistoryClient.getUserHistoryByUserId(userId, pageRequestDto).getBody()).getContent().keySet();
        var results = Objects.requireNonNull(paymentHistoryClient.getUserHistoryByUserId(userId, pageRequestDto).getBody()).getContent();
        var pages = Objects.requireNonNull(paymentHistoryClient.getUserHistoryByUserId(userId, pageRequestDto).getBody()).getTotalPages();
        var elements = Objects.requireNonNull(paymentHistoryClient.getUserHistoryByUserId(userId, pageRequestDto).getBody()).getTotalElements();
        for (Date a : dates) {
            List<ResponsePaymentHistoryDto> list = new ArrayList<>();
            for (RequestPaymentHistoryDto historyDto : results.get(a)) {
                list.add(paymentHistoryMapper.requestToResponse(historyDto));
            }
            map.put(a, list);
        }
        return PageResponse.<Date, List<ResponsePaymentHistoryDto>>builder()
                .totalPages(pages)
                .totalElements(elements)
                .content(map)
                .build();
    }

    public ReceiptDto getBySenderRequestId(String senderRequestId) {
        return paymentHistoryClient.getBySenderRequestId(senderRequestId).getBody();
    }

    public ReceiptDto getById(Long id){
        return paymentHistoryClient.getById(id).getBody();
    }

   /*

    @Transactional(readOnly = true)
    public Map<String, BigDecimal> getStatisticsByUserId(StatisticDto statisticDto) {
        String userId = statisticDto.userId();
        if (userService.userExistById(userId)) {
            List<PaymentHistory> paymentHistoryList = paymentHistoryRepository
                    .findAllByUserIdAndDateBetween(statisticDto.userId(), statisticDto.startDate(), statisticDto.endDate());

            Map<String, BigDecimal> categoryPayments = new HashMap<>();

            for (PaymentHistory ph : paymentHistoryList) {
                Integer serviceId = ph.getServiceId();
                BigDecimal amount = ph.getAmount();
                String categoryName = categoryService.getById(serviceService.getCategoryIdById(serviceId)).getName();

                if (!categoryPayments.containsKey(categoryName)) {
                    categoryPayments.put(categoryName, amount);
                } else {
                    BigDecimal total = categoryPayments.get(categoryName);
                    total = total.add(ph.getAmount());
                    categoryPayments.put(categoryName, total);
                }
            }
            return categoryPayments;
        } else throw new UserNotFoundException("user could not found by id: " + userId);
    }*/
}
