package com.example.bookshop.repository;

import com.example.bookshop.model.CartItem;
import com.example.bookshop.model.ShoppingCart;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findCartItemById(Long id);

    Set<CartItem> findAllByShoppingCart(ShoppingCart shoppingCart);
}
