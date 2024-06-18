package com.greentechpay.bff.client.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class NameIdListDto {
    private List<Integer> ServiceIdList;
    private RequestType Type;
}
