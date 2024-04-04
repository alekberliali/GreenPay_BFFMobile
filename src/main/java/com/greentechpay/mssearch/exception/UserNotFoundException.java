package com.greentechpay.mssearch.exception;

import com.greentechpay.mssearch.dto.ReceiptHistoryDto;
import com.greentechpay.mssearch.dto.response.PageResponse;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
