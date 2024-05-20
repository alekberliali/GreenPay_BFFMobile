package com.greentechpay.bff;

import com.greentechpay.bff.client.RetrieveErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.greentechpay.bff.client")
public class BffMobileApplication {

    public static void main(String[] args) {
        SpringApplication.run(BffMobileApplication.class, args);
    }
}
