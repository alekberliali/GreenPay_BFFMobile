package com.greentechpay.bff.client.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Wallet {
    private String iban;
}
