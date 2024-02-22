package com.example.bookshop.mapper;

import com.example.bookshop.dto.AddCartItemRequestDto;
import com.example.bookshop.dto.CartItemResponseDto;
import com.example.bookshop.model.Book;
import com.example.bookshop.model.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapperImpl implements CartItemMapper {
    @Override
    public CartItem toModel(AddCartItemRequestDto requestDto) {
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(requestDto.getQuantity());
        Book book = new Book();
        book.setId(requestDto.getBookId());
        cartItem.setBook(book);
        return cartItem;
    }

    @Override
    public CartItemResponseDto toDto(CartItem cartItem) {
        CartItemResponseDto responseDto = new CartItemResponseDto();
        responseDto.setId(cartItem.getId());
        responseDto.setQuantity(cartItem.getQuantity());
        responseDto.setBookId(cartItem.getBook().getId());
        responseDto.setBookTitle(cartItem.getBook().getTitle());
        return responseDto;
    }
}
