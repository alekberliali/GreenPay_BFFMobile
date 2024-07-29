package com.greentechpay.bff.client.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Wallet {
    private List<String> iban;
}
