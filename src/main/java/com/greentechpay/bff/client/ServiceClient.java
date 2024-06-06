package com.greentechpay.bff.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service", url = "${app.feign.service.config.url}")
public interface ServiceClient {
    @GetMapping(value = "/{id}")
    String getNameById(@PathVariable Integer id);
}
