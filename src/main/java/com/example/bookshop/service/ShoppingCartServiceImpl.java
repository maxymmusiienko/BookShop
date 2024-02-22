package com.example.bookshop.service;

import com.example.bookshop.dto.AddCartItemRequestDto;
import com.example.bookshop.dto.ShoppingCartResponseDto;
import com.example.bookshop.dto.UpdateCartItemRequestDto;
import com.example.bookshop.exception.EntityNotFoundException;
import com.example.bookshop.mapper.CartItemMapper;
import com.example.bookshop.mapper.ShoppingCartMapper;
import com.example.bookshop.model.Book;
import com.example.bookshop.model.CartItem;
import com.example.bookshop.model.ShoppingCart;
import com.example.bookshop.model.User;
import com.example.bookshop.repository.BookRepository;
import com.example.bookshop.repository.CartItemRepository;
import com.example.bookshop.repository.ShoppingCartRepository;
import com.example.bookshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final BookRepository bookRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final CartItemMapper cartItemMapper;
    private final ShoppingCartMapper shoppingCartMapper;

    //TODO deal with cart items set

    @Override
    public ShoppingCartResponseDto findCart(String email, Pageable pageable) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(
                        () -> new EntityNotFoundException("Can`t find user by email " + email));
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUser(user);
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
            shoppingCart.setUser(user);
        }
        //Set<CartItem> cartItems = cartItemRepository.findAllByShoppingCart(shoppingCart);
        //shoppingCart.setCartItems(cartItems);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public ShoppingCartResponseDto addItem(String email, AddCartItemRequestDto requestDto) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(
                        () -> new EntityNotFoundException("Can`t find user by email " + email));
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUser(user);
        CartItem cartItem = cartItemMapper.toModel(requestDto);
        cartItem.setShoppingCart(shoppingCart);
        Long bookId = cartItem.getBook().getId();
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Can`t find book by id" + bookId));
        cartItem.setBook(book);
        cartItemRepository.save(cartItem);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public ShoppingCartResponseDto updateItem(Long id, String email,
                                              UpdateCartItemRequestDto requestDto) {
        CartItem cartItem = cartItemRepository.findCartItemById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can`t find cart item by id " + id));
        cartItem.setId(id);
        cartItem.setQuantity(requestDto.getQuantity());
        cartItemRepository.save(cartItem);
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(
                    () -> new EntityNotFoundException("Can`t find user by email " + email));
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUser(user);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public void deleteById(Long id) {
        cartItemRepository.deleteById(id);
    }
}
