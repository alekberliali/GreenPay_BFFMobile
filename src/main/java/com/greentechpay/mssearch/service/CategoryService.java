package com.greentechpay.mssearch.service;

import com.greentechpay.mssearch.dto.response.ResponseCategoryDto;
import com.greentechpay.mssearch.entity.Category;
import com.greentechpay.mssearch.mapper.CategoryMapper;
import com.greentechpay.mssearch.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<ResponseCategoryDto> search() {
        return categoryRepository.categories().stream()
                .map(categoryMapper::entityToDto)
                .toList();
    }

    protected Category getById(Integer id) {
        return categoryRepository.findById(id).orElseThrow();
    }
}
