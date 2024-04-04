package com.greentechpay.mssearch.service;

import com.greentechpay.mssearch.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    protected boolean userExistById(String userId){
       return userRepository.existsById(userId);
    }
}
