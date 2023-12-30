package com.example.bookshop.dto;

import com.example.bookshop.annotations.FieldMatch;
import com.example.bookshop.annotations.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@FieldMatch(first = "password", second = "repeatPassword",
        message = "Can`t register a user: password and repeated password are not equals")
public class RegisterUserRequestDto {
    @Email
    @Length(max = 100)
    private String email;
    @ValidPassword
    private String password;
    @ValidPassword
    private String repeatPassword;
    @NotBlank
    @Length(max = 40)
    private String firstName;
    @NotBlank
    @Length(max = 40)
    private String lastName;
    @NotBlank
    @Length(max = 100)
    private String shippingAddress;
}
