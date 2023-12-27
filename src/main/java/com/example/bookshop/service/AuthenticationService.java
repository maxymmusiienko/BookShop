package com.example.bookshop.service;

import com.example.bookshop.dto.UserLoginRequestDto;
import com.example.bookshop.dto.UserLoginResponseDto;

public interface AuthenticationService {
    UserLoginResponseDto authenticate(UserLoginRequestDto requestDto);
}
