package com.greentechpay.bff.service;

import com.greentechpay.bff.client.PaymentHistoryClient;
import com.greentechpay.bff.client.ServiceClient;
import com.greentechpay.bff.client.request.RequestIdList;
import com.greentechpay.bff.client.response.PaymentHistory;
import com.greentechpay.bff.dto.Status;
import com.greentechpay.bff.dto.TransferType;
import com.greentechpay.bff.dto.request.*;
import com.greentechpay.bff.dto.response.PageResponse;
import com.greentechpay.bff.dto.response.ReceiptDto;
import com.greentechpay.bff.dto.response.PaymentHistoryDto;
import com.greentechpay.bff.dto.response.ResponseDto;
import com.greentechpay.bff.mapper.PaymentHistoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentHistoryService {

    private final PaymentHistoryClient paymentHistoryClient;
    private final PaymentHistoryMapper paymentHistoryMapper;
    private final ServiceClient serviceClient;

    private Status getStatus(Status status) {
        if (status.equals(Status.Success) || status.equals(Status.TransactionSuccessfully)) {
            return Status.Success;
        } else if (status.equals(Status.Pending) || status.equals(Status.TransactinCreated) ||
                status.equals(Status.SendingToVendor) || status.equals(Status.CreatedAtVendor)
                || status.equals(Status.RequestBeingProcessed) || status.equals(Status.TransactionProgress)) {
            return Status.Pending;
        } else {
            return Status.Fail;
        }
    }

    private Map<Integer, String> getServiceNames(String agentName, String agentPassword, String agentId,
                                                 String accessToken, List<PaymentHistory> paymentHistoryList) {
        Set<Integer> requestIdSet = new HashSet<>();
        for (var ph : paymentHistoryList) {
            requestIdSet.add(ph.getServiceId());
        }
        requestIdSet.remove(null);
        if (requestIdSet.size() > 0) {
            var idList = RequestIdList.builder()
                    .serviceIds(requestIdSet.stream().toList())
                    .build();
            return serviceClient.getServiceNamesById(agentName, agentPassword, agentId, accessToken, idList)
                    .getData().getServicesName();
        } else return new HashMap<>();
    }

    public PageResponse<Map<LocalDate, List<PaymentHistoryDto>>>
    getAllByPage(String agentName, String agentPassword, String agentId, String accessToken, Integer page, Integer size,
                 String userId, String senderIban, LocalDate startDate, LocalDate endDate) {

        PageRequestDto pageRequestDto = new PageRequestDto(page, size);
        PaymentHistoryCriteria paymentHistoryCriteria = new PaymentHistoryCriteria(userId, senderIban, startDate, endDate);
        FilterDto<PaymentHistoryCriteria> filterDto = new FilterDto<>(pageRequestDto, paymentHistoryCriteria);

        var request = Objects.requireNonNull(paymentHistoryClient.getUserHistoryByUserId(filterDto).getBody());
        var results = request.getContent();
        var filter = results.stream().filter(paymentHistory -> paymentHistory.getStatus() != Status.Created).toList();
        var pages = request.getTotalPages();
        var elements = request.getTotalElements();

        Map<Integer, String> serviceMap = getServiceNames(agentName, agentPassword, agentId, accessToken, request.getContent());
        serviceMap.put(null, "");
        Map<LocalDate, List<PaymentHistoryDto>> map = new HashMap<>();
        for (PaymentHistory dto : filter) {
            LocalDate date = dto.getPaymentDate().toLocalDate();
            List<PaymentHistoryDto> list = map.getOrDefault(date, new ArrayList<>());
            var responseDto = paymentHistoryMapper.requestToResponse(dto);
            responseDto.setStatus(getStatus(dto.getStatus()));
            responseDto.setServiceName(serviceMap.get(dto.getServiceId()));
            list.add(responseDto);
            map.put(date, list);
        }

        Map<LocalDate, List<PaymentHistoryDto>> sortedMap = new TreeMap<>(Collections.reverseOrder());
        sortedMap.putAll(map);

        return PageResponse.<Map<LocalDate, List<PaymentHistoryDto>>>builder()
                .totalPages(pages)
                .totalElements(elements)
                .content(sortedMap)
                .build();
    }

   /* public ReceiptDto getBySenderRequestId(String senderRequestId) {
        var receipt = paymentHistoryClient.getBySenderRequestId(senderRequestId).getBody();
        String phoneNumber = authClient.getNumberById(Objects.requireNonNull(receipt).getFrom());
        receipt.setFrom(phoneNumber);
        return receipt;
    }*/

    public ResponseDto<Map<String, BigDecimal>> getStatisticsByUserId(String iban, LocalDate startDate, LocalDate endDate) {
        StatisticDto statisticDto = new StatisticDto(iban, startDate, endDate); //TODO convert record to class

        return ResponseDto.<Map<String, BigDecimal>>builder()
                .data(paymentHistoryClient.getStatisticsByUserId(statisticDto).getBody())
                .build();
    }

    public ReceiptDto getById(String agentName, String agentPassword, String agentId, String accessToken, Long id) {
        var request = paymentHistoryClient.getById(id).getBody();
        assert request != null;
        List<PaymentHistory> paymentHistoryList = new ArrayList<>();
        paymentHistoryList.add(request);
        Map<Integer, String> serviceMap = getServiceNames(agentName, agentPassword, agentId, accessToken,
                paymentHistoryList);
        serviceMap.put(null, "");
        if (request.getTransferType().equals(TransferType.BillingPayment)) {
            return ReceiptDto.builder()
                    .amount(request.getAmount())
                    .serviceName(serviceMap.get(request.getServiceId()))
                    .senderRequestId(request.getSenderRequestId())
                    .from(serviceMap.get(request.getServiceId()))
                    .field(request.getRequestField())
                    .categoryName(request.getCategoryName())
                    .paymentDate(request.getPaymentDate())
                    .currency(request.getCurrency())
                    .type(request.getTransferType())
                    .status(getStatus(request.getStatus()))
                    .build();
        } else if (request.getTransferType().equals(TransferType.IbanToIban) ||
                request.getTransferType().equals(TransferType.IbanToPhoneNumber)) {
            return ReceiptDto.builder()
                    .amount(request.getAmount())
                    .senderRequestId(request.getSenderRequestId())
                    .from(request.getSenderIban())
                    .to(request.getReceiverIban())
                    .field(request.getRequestField())
                    .categoryName(request.getCategoryName())
                    .paymentDate(request.getPaymentDate())
                    .currency(request.getCurrency())
                    .type(request.getTransferType())
                    .status(getStatus(request.getStatus()))
                    .build();
        } else if (request.getTransferType().equals(TransferType.BalanceToCard)) {
            return ReceiptDto.builder()
                    .amount(request.getAmount())
                    .senderRequestId(request.getSenderRequestId())
                    .from(request.getSenderIban())
                    .field(request.getRequestField())
                    .categoryName(request.getCategoryName())
                    .paymentDate(request.getPaymentDate())
                    .currency(request.getCurrency())
                    .type(request.getTransferType())
                    .status(getStatus(request.getStatus()))
                    .build();
        } else if (request.getTransferType().equals(TransferType.CardToBalance)) {
            return ReceiptDto.builder()
                    .amount(request.getAmount())
                    .senderRequestId(request.getSenderRequestId())
                    .field(request.getRequestField())
                    .categoryName(request.getCategoryName())
                    .paymentDate(request.getPaymentDate())
                    .currency(request.getCurrency())
                    .type(request.getTransferType())
                    .status(getStatus(request.getStatus()))
                    .build();
        } else {
            return null;
        }
    }
}
