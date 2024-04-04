package com.greentechpay.mssearch.controller.v1;

import com.greentechpay.mssearch.dto.response.ServiceDto;
import com.greentechpay.mssearch.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/service")
public class ServiceController {

    private final ServiceService serviceService;

    @GetMapping("/search")
    public ResponseEntity<List<ServiceDto>> search(@RequestParam String value) {
        return ResponseEntity.ok(serviceService.search(value));
    }
}
