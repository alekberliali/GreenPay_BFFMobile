package com.greentechpay.mssearch.controller.v1;

import com.greentechpay.mssearch.dto.response.ResponseCategoryDto;
import com.greentechpay.mssearch.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<ResponseCategoryDto>> getAllCategoriesWithServices(){
        return ResponseEntity.ok(categoryService.search());
    }
}
