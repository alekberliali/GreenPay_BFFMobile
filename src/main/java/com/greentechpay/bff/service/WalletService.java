package com.greentechpay.bff.service;

import com.greentechpay.bff.client.WalletClient;
import com.greentechpay.bff.client.request.Wallet;
import com.greentechpay.bff.client.response.Base;
import com.greentechpay.bff.client.response.BaseResponse;
import com.greentechpay.bff.client.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletClient walletClient;

    protected String getPhoneNumberByIban(String agentName, String agentPassword, String agentId,
                                          String accessToken, String authorizationToken, String iban) {

        return walletClient.getPhoneNumberByIban(agentName, agentPassword, agentId, accessToken,
                authorizationToken, iban).getData().getPhoneNumber();
    }
}
