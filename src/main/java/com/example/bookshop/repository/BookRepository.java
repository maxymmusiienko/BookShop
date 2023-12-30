package com.example.bookshop.repository;

import com.example.bookshop.model.Book;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByCategoriesId(Pageable pageable, Long id);
}
