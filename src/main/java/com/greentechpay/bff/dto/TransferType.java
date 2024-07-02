package com.greentechpay.bff.dto;

public enum TransferType {
    IbanToPhoneNumber,
    IbanToIban,
    BalanceToCard,
    CardToBalance,
    BillingPayment,
    Qr,
    Nfc,
}
