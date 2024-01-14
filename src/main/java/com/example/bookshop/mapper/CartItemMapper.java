package com.example.bookshop.mapper;

import com.example.bookshop.dto.AddCartItemRequestDto;
import com.example.bookshop.dto.CartItemResponseDto;
import com.example.bookshop.model.CartItem;

public interface CartItemMapper {
    CartItem toModel(AddCartItemRequestDto requestDto);

    CartItemResponseDto toDto(CartItem cartItem);
}
