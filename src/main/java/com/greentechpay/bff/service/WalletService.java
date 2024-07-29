package com.greentechpay.bff.service;

import com.greentechpay.bff.client.WalletClient;
import com.greentechpay.bff.client.request.Wallet;
import com.greentechpay.bff.client.response.Base;
import com.greentechpay.bff.client.response.BaseResponse;
import com.greentechpay.bff.client.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletClient walletClient;

    protected Map<String, String> getPhoneNumberByIban(String agentName, String agentPassword, String agentId,
                                       String accessToken, String authorizationToken, String iban) {
        var request = Wallet.builder()
                .iban(List.of(iban))
                .build();
        return walletClient.getPhoneNumberByIban(agentName, agentPassword, agentId, accessToken,
                authorizationToken, request).getData().getIbanPhonePairs();
    }
}
