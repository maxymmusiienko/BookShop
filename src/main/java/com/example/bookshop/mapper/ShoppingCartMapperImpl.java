package com.example.bookshop.mapper;

import com.example.bookshop.dto.ShoppingCartResponseDto;
import com.example.bookshop.model.ShoppingCart;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ShoppingCartMapperImpl implements ShoppingCartMapper {
    private final CartItemMapper cartItemMapper;

    @Override
    public ShoppingCartResponseDto toDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto responseDto = new ShoppingCartResponseDto();
        responseDto.setId(shoppingCart.getId());
        responseDto.setUserId(shoppingCart.getUser().getId());
        responseDto.setCartItems(shoppingCart.getCartItems().stream()
                .map(cartItemMapper::toDto)
                .collect(Collectors.toSet()));
        return responseDto;
    }
}
