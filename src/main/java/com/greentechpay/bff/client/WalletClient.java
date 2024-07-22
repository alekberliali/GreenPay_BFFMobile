package com.greentechpay.bff.client;

import com.greentechpay.bff.client.request.Wallet;
import com.greentechpay.bff.client.response.Base;
import com.greentechpay.bff.client.response.BaseResponse;
import com.greentechpay.bff.client.response.ResponseData;
import com.greentechpay.bff.exception.WalletClientExceptionDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "wallet",
        url = "${app.feign.wallet.url}",
        configuration = WalletClientExceptionDecoder.class)
public interface WalletClient {

    @GetMapping("/IbanAccount/GetPhoneNumberByIban")
    BaseResponse<ResponseData> getPhoneNumberByIban(@RequestHeader(value = "agent-name") String agentName,
                                                    @RequestHeader(value = "agent-password") String agentPassword,
                                                    @RequestHeader(value = "agent-id") String agentId,
                                                    @RequestHeader(value = "access-token") String accessToken,
                                                    @RequestHeader(value = "Authorization") String authorization,
                                                    @RequestParam String iban);
}
