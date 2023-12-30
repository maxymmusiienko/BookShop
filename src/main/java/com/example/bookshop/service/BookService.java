package com.example.bookshop.service;

import com.example.bookshop.dto.BookDto;
import com.example.bookshop.dto.BookWithoutCategoriesDto;
import com.example.bookshop.dto.CreateBookRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface BookService {
    BookDto save(CreateBookRequestDto book);

    List<BookWithoutCategoriesDto> findAll(Pageable pageable);

    BookDto getById(Long id);

    BookDto update(Long id, CreateBookRequestDto book);

    void deleteById(Long id);

    List<BookDto> findAllByCategory(Pageable pageable, Long id);
}
