package com.example.bookshop.repository;

import com.example.bookshop.model.CartItem;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findCartItemById(Long id);
}
