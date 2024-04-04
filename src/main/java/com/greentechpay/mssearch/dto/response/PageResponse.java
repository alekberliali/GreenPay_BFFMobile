package com.greentechpay.mssearch.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class PageResponse<K,V> {
    private Long totalElements;
    private Integer totalPages;
    private Map<K,V> content;
}
