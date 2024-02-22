package com.example.bookshop.repository;

import com.example.bookshop.model.ShoppingCart;
import com.example.bookshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    ShoppingCart findShoppingCartByUser(User user);
}
