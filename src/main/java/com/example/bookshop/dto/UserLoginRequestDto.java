package com.example.bookshop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserLoginRequestDto {
    @NotBlank
    @Length(max = 100)
    @Email
    private String email;
    @NotBlank
    @Length(min = 8, max = 25)
    private String password;
}
