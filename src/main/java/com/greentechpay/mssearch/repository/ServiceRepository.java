package com.greentechpay.mssearch.repository;

import com.greentechpay.mssearch.entity.AppService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceRepository extends JpaRepository<AppService, Integer> {
    @Query("select s.categoryId.id from AppService s where s.id=:id")
    Integer findCategoryIdById(Integer id);

    @Query("SELECT s FROM AppService s WHERE s.name LIKE CONCAT(:value,'%')")
    List<AppService> search(String value);
}
