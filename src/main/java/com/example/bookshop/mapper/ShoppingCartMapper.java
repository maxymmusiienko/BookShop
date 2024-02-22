package com.example.bookshop.mapper;

import com.example.bookshop.dto.ShoppingCartResponseDto;
import com.example.bookshop.model.ShoppingCart;

public interface ShoppingCartMapper {
    ShoppingCartResponseDto toDto(ShoppingCart shoppingCart);
}
