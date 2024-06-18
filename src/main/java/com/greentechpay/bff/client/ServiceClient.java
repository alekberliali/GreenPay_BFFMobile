package com.greentechpay.bff.client;

import com.greentechpay.bff.client.request.NameIdListDto;
import com.greentechpay.bff.client.request.RequestType;
import com.greentechpay.bff.client.response.BaseResponse;
import com.greentechpay.bff.client.response.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "service", url = "${app.feign.service.config.url}")
public interface ServiceClient {
    @PostMapping("/Entities/GetEntityNames")
    BaseResponse<ResponseData> getNameById(NameIdListDto nameIdListDto);

}
