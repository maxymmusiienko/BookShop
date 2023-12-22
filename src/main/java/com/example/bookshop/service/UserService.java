package com.example.bookshop.service;

import com.example.bookshop.dto.RegisterUserRequestDto;
import com.example.bookshop.dto.UserResponseDto;

public interface UserService {
    UserResponseDto register(RegisterUserRequestDto requestDto);
}
