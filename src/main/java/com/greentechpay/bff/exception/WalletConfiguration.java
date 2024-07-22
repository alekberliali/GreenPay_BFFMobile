package com.greentechpay.bff.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class WalletConfiguration {
    public WalletConfiguration() {
    }

    @Bean
    WalletClientExceptionDecoder exceptionDecoder(ObjectMapper objectMapper) {
        return new WalletClientExceptionDecoder(objectMapper);
    }
}
