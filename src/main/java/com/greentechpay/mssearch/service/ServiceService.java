package com.greentechpay.mssearch.service;

import com.greentechpay.mssearch.dto.response.ServiceDto;
import com.greentechpay.mssearch.entity.AppService;
import com.greentechpay.mssearch.exception.ServiceNotFoundException;
import com.greentechpay.mssearch.mapper.ServiceMapper;
import com.greentechpay.mssearch.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceService {
    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    public AppService getById(Integer id) {
        return serviceRepository.findById(id).orElseThrow(() -> new ServiceNotFoundException("service could not found by id: " + id));
    }

    protected Integer getCategoryIdById(Integer id){
        return serviceRepository.findCategoryIdById(id);
    }

    //TODO exceptionlar ve gelen string uzunluqu nezere alinacaq
    public List<ServiceDto> search(String value) {
        return serviceRepository.search(value).stream()
                .map(serviceMapper::entityToDto)
                .toList();
    }
}
