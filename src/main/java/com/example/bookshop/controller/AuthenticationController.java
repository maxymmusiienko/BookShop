package com.example.bookshop.controller;

import com.example.bookshop.dto.RegisterUserRequestDto;
import com.example.bookshop.dto.UserLoginRequestDto;
import com.example.bookshop.dto.UserLoginResponseDto;
import com.example.bookshop.dto.UserResponseDto;
import com.example.bookshop.exception.RegistrationException;
import com.example.bookshop.service.AuthenticationService;
import com.example.bookshop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication manager", description = "Endpoints for authentication")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    @Operation(summary = "Login a user", description = "This method logins user")
    public UserLoginResponseDto login(@RequestBody
                                      @Valid UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }

    @PostMapping("/registration")
    @Operation(summary = "Register a user", description = "This method registers user")
    public UserResponseDto register(@RequestBody
                                        @Valid RegisterUserRequestDto requestDto)
                                        throws RegistrationException {
        return userService.register(requestDto);
    }
}
