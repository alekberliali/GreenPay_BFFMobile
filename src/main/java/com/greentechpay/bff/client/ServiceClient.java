package com.greentechpay.bff.client;

import com.greentechpay.bff.client.request.RequestIdList;
import com.greentechpay.bff.client.response.BaseResponse;
import com.greentechpay.bff.client.response.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "service", url = "${app.feign.service.config.url}")
public interface ServiceClient {
    @PostMapping("/Service/GetServicesNameById")
    BaseResponse<ResponseData> getServiceNamesById(@RequestHeader(value = "agent-name") String agentName,
                                                   @RequestHeader(value = "agent-password") String agentPassword,
                                                   @RequestHeader(value = "agent-id") String agentId,
                                                   @RequestHeader(value = "access-token") String accessToken,
                                                   @RequestBody RequestIdList requestIdList);

}
