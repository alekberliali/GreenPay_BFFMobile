package com.greentechpay.mssearch.exception;

public class ServiceNotFoundException extends RuntimeException{
    public ServiceNotFoundException(String message) {
        super(message);
    }
}
