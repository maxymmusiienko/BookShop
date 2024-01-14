package com.example.bookshop.dto;

import lombok.Data;

@Data
public class AddCartItemRequestDto {
    private Long bookId;
    private int quantity;
}
