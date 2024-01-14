package com.example.bookshop.service;

import com.example.bookshop.dto.AddCartItemRequestDto;
import com.example.bookshop.dto.ShoppingCartResponseDto;
import com.example.bookshop.dto.UpdateCartItemRequestDto;
import org.springframework.data.domain.Pageable;

public interface ShoppingCartService {
    ShoppingCartResponseDto findCart(String email, Pageable pageable);

    ShoppingCartResponseDto addItem(String email, AddCartItemRequestDto requestDto);

    ShoppingCartResponseDto updateItem(Long id, String email, UpdateCartItemRequestDto requestDto);

    void deleteById(Long id);
}
