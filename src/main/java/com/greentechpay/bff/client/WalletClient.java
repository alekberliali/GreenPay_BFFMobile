package com.greentechpay.bff.client;

import com.greentechpay.bff.client.request.Wallet;
import com.greentechpay.bff.client.response.BaseResponse;
import com.greentechpay.bff.client.response.ResponseData;
import com.greentechpay.bff.exception.WalletClientExceptionDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "wallet", url = "${app.feign.wallet.url}", configuration = WalletClientExceptionDecoder.class)
public interface WalletClient {

    @PostMapping("/IbanAccount/GetPhoneNumbersForIban")
    BaseResponse<ResponseData> getPhoneNumberByIban(@RequestHeader(value = "agent-name") String agentName,
                                                    @RequestHeader(value = "agent-password") String agentPassword,
                                                    @RequestHeader(value = "agent-id") String agentId,
                                                    @RequestHeader(value = "access-token") String accessToken,
                                                    @RequestHeader(value = "Authorization") String authorization,
                                                    @RequestBody Wallet wallet);
}
