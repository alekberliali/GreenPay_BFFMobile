package com.greentechpay.bff.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "auth", url = "${app.feign.auth.config.url}")
public interface AuthClient {
    @GetMapping(value = "/number/{id}")
    String getNumberById(@PathVariable String id);
}
