package com.greentechpay.mssearch.repository;

import com.greentechpay.mssearch.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("SELECT DISTINCT c FROM Category c JOIN FETCH c.appServiceList s WHERE s.showService = true")
    List<Category> categories();
}
