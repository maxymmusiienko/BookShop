package com.example.bookshop.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;
import lombok.Data;
import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

@Data
public class CreateBookRequestDto {
    @NotBlank
    @Length(max = 100)
    private String title;
    @NotBlank
    @Length(max = 100)
    private String author;
    @ISBN(type = ISBN.Type.ISBN_13)
    private String isbn;
    @NotNull
    @Min(0)
    private BigDecimal price;
    @NotBlank
    @Length(min = 20, max = 300)
    private String description;
    @URL
    private String coverImage;
    private Set<Long> categories;
}
