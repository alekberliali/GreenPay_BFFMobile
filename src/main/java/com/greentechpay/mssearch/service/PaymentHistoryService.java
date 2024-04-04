package com.greentechpay.mssearch.service;

import com.greentechpay.mssearch.dto.request.PageRequestDto;
import com.greentechpay.mssearch.dto.request.StatisticDto;
import com.greentechpay.mssearch.dto.response.PageResponse;
import com.greentechpay.mssearch.dto.ReceiptHistoryDto;
import com.greentechpay.mssearch.dto.response.ResponseReceiptDto;
import com.greentechpay.mssearch.entity.AppService;
import com.greentechpay.mssearch.entity.PaymentHistory;
import com.greentechpay.mssearch.exception.ReceiptNotFoundException;
import com.greentechpay.mssearch.exception.UserNotFoundException;
import com.greentechpay.mssearch.mapper.CustomMapper;
import com.greentechpay.mssearch.repository.PaymentHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PaymentHistoryService {
    private final PaymentHistoryRepository paymentHistoryRepository;
    private final CustomMapper customMapper;
    private final UserService userService;
    private final ServiceService serviceService;
    private final CategoryService categoryService;

    public PageResponse<Date, List<ReceiptHistoryDto>> getAllByPage(String userId, PageRequestDto pageRequestDto) {
        if (userService.userExistById(userId)) {
            var pageRequest = PageRequest.of(pageRequestDto.page(), pageRequestDto.size());
            var result = paymentHistoryRepository.findAllBy(userId, pageRequest);

            Map<Date, List<ReceiptHistoryDto>> resultMap = new HashMap<>();
            for (PaymentHistory paymentHistory : result.getContent()) {
                Date date = paymentHistory.getDate();
                List<ReceiptHistoryDto> list = resultMap.getOrDefault(date, new ArrayList<>());
                list.add(customMapper.converter(paymentHistory));
                resultMap.put(date, list);
            }

            Map<Date, List<ReceiptHistoryDto>> sortedMap = new TreeMap<>(Collections.reverseOrder());
            sortedMap.putAll(resultMap);

            return PageResponse.<Date, List<ReceiptHistoryDto>>builder()
                    .totalElements(result.getTotalElements())
                    .totalPages(result.getTotalPages())
                    .content(sortedMap)
                    .build();
        } else {
            throw new UserNotFoundException("user could not find by id: " + userId);
        }
    }

    public ResponseReceiptDto getById(String id) {
        PaymentHistory paymentHistory = paymentHistoryRepository.findById(id).orElseThrow(() -> new ReceiptNotFoundException("receipt could not found by id: " + id));
        if (paymentHistory.getServiceId() != 0) {
            AppService service = serviceService.getById(paymentHistory.getServiceId());
            return ResponseReceiptDto.builder()
                    .amount(paymentHistory.getAmount())
                    .paymentDate(paymentHistory.getPaymentDate())
                    .senderRequestId(paymentHistory.getSenderRequestId())
                    .name(service.getName())
                    .from(paymentHistory.getUser().getPhoneNumber())
                    .to(paymentHistory.getToUser())
                    .currency(paymentHistory.getCurrency())
                    .type(paymentHistory.getTransferType())
                    .status(paymentHistory.getPaymentStatus())
                    .build();

        } else {
            return ResponseReceiptDto.builder()
                    .amount(paymentHistory.getAmount())
                    .paymentDate(paymentHistory.getPaymentDate())
                    .senderRequestId(paymentHistory.getSenderRequestId())
                    .name(paymentHistory.getTransferType().name())
                    .from(paymentHistory.getUser().getPhoneNumber())
                    .to(paymentHistory.getToUser())
                    .currency(paymentHistory.getCurrency())
                    .type(paymentHistory.getTransferType())
                    .status(paymentHistory.getPaymentStatus())
                    .build();
        }
    }

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
    }
}
