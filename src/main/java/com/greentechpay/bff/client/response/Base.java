package com.greentechpay.bff.client.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Base {
    @JsonProperty(value = "data")
    private Datum data;

}
