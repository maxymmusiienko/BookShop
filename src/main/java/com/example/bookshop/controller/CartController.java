package com.example.bookshop.controller;

import com.example.bookshop.dto.AddCartItemRequestDto;
import com.example.bookshop.dto.ShoppingCartResponseDto;
import com.example.bookshop.dto.UpdateCartItemRequestDto;
import com.example.bookshop.model.User;
import com.example.bookshop.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "book cart management", description = "Endpoints for managing shopping cart")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final ShoppingCartService shoppingCartService;

    @GetMapping
    @Operation(summary = "Find shopping cart", description = "This method finds shopping cart")
    public ShoppingCartResponseDto findCart(Authentication authentication, Pageable pageable) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.findCart(user.getEmail(), pageable);
    }

    @PostMapping
    @Operation(summary = "Adds a book to shopping cart",
            description = "This method adds a book to cart")
    public ShoppingCartResponseDto addItem(
            @RequestBody AddCartItemRequestDto cartItemRequestDto,
            Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.addItem(user.getEmail(), cartItemRequestDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a book in shopping cart",
            description = "This method updates book in cart")
    public ShoppingCartResponseDto updateItem(
            @PathVariable Long id,
            @RequestBody UpdateCartItemRequestDto cartItemRequestDto,
            Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.updateItem(id, user.getEmail(), cartItemRequestDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a book from cart",
            description = "This method deletes a book from cart")
    public void deleteById(@PathVariable Long id) {
        shoppingCartService.deleteById(id);
    }
}
