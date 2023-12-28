package com.example.bookshop.controller;

import com.example.bookshop.dto.RegisterUserRequestDto;
import com.example.bookshop.dto.UserLoginRequestDto;
import com.example.bookshop.dto.UserLoginResponseDto;
import com.example.bookshop.dto.UserResponseDto;
import com.example.bookshop.exception.RegistrationException;
import com.example.bookshop.service.AuthenticationService;
import com.example.bookshop.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody
                                      @Valid UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }

    @PostMapping("/registration")
    public UserResponseDto register(@RequestBody
                                        @Valid RegisterUserRequestDto requestDto)
                                        throws RegistrationException {
        return userService.register(requestDto);
    }
}
