package com.greentechpay.bff.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class WalletClientExceptionDecoder implements ErrorDecoder {
    private static final Logger log = LoggerFactory.getLogger(WalletClientExceptionDecoder.class);
    private final ObjectMapper objectMapper;

    public WalletClientExceptionDecoder(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            log.error("WalletClientExceptionDecoder: {}, response: {}", methodKey, response);
            InputStream inputStream = response.body().asInputStream();
            var s = "sj";

        } catch (Throwable e) {

        }
        return null;
    }
}
