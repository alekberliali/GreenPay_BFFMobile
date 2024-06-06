package com.greentechpay.bff.client;

import com.greentechpay.bff.exception.ExceptionMessage;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
public class RetrieveErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        ExceptionMessage exceptionMessage;
        try (InputStream bodyIs = response.body().asInputStream()) {
            exceptionMessage = new ExceptionMessage(response.headers().get("date").toString(),
                    response.status(),
                    HttpStatus.resolve(response.status()).getReasonPhrase(),
                    IOUtils.toString(bodyIs, StandardCharsets.UTF_8),
                    response.request().url());
        }catch (IOException e){
            return new Exception(e.getMessage());
        }switch (response.status()) {
            case 404:
                throw new RuntimeException(exceptionMessage.message());
            default:
                return errorDecoder.decode(methodKey, response);
        }
    }
}
