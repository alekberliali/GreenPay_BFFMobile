package com.greentechpay.bff.client.response;

import lombok.Data;

import java.util.Map;

@Data
public class ResponseData {
    private Map<Integer, String> servicesName;
}
