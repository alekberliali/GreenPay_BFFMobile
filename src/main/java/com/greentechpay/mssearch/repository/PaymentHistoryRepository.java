package com.greentechpay.mssearch.repository;

import com.greentechpay.mssearch.entity.PaymentHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;


public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, String> {

    @Query("SELECT rh FROM PaymentHistory rh WHERE rh.user.id=:userId ORDER BY rh.paymentDate DESC")
    Page<PaymentHistory> findAllBy(@Param("userId") String userId, PageRequest pageRequest);

    List<PaymentHistory> findAllByUserIdAndDateBetween(String id, Date startDate, Date endDate);
}
