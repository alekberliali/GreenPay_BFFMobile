package com.greentechpay.bff.client.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RequestIdList {
    private List<Integer> serviceIds;
}
