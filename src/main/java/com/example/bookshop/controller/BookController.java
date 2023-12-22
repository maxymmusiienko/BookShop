package com.example.bookshop.controller;

import com.example.bookshop.annotations.BookIdParameter;
import com.example.bookshop.dto.BookDto;
import com.example.bookshop.dto.CreateBookRequestDto;
import com.example.bookshop.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "book management", description = "Endpoints for managing books")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    @Operation(summary = "Find all books", description = "This method finds all books")
    public List<BookDto> findAll(Pageable pageable) {
        return bookService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a book by id", description = "This method finds a book by given id")
    public BookDto getById(@BookIdParameter @PathVariable Long id) {
        return bookService.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Save a book", description = "This method add a new book to DB")
    public BookDto save(@Parameter(
            name = "requestDto",
            description = "This is a request DTO of a book",
            example = """
                    {
                      "title": "Stephen King",
                      "author": "Shining",
                      "isbn": "978-112-233-44-55",
                      "price": 40.0,
                      "description": "scary booooook",
                      "coverImage": "https://example.com/shining"
                    }
                    """,
            required = true) @RequestBody @Valid CreateBookRequestDto requestDto) {
        return bookService.save(requestDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book", description = "This method soft-deletes a book from DB")
    public void delete(@BookIdParameter @PathVariable Long id) {
        bookService.deleteById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public BookDto update(@BookIdParameter @PathVariable Long id,
                          @RequestBody @Valid CreateBookRequestDto requestDto) {
        return bookService.update(id, requestDto);
    }
}
