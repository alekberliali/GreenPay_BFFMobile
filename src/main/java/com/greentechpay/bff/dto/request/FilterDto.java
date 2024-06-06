package com.greentechpay.bff.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterDto<T> {
    private PageRequestDto pageRequestDto;
    private T data;
}
