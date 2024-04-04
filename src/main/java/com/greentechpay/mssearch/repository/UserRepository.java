package com.greentechpay.mssearch.repository;

import com.greentechpay.mssearch.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, String> {
}
