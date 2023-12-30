package com.example.bookshop.controller;

import com.example.bookshop.annotations.CategoryIdParameter;
import com.example.bookshop.dto.BookDto;
import com.example.bookshop.dto.CategoryRequestDto;
import com.example.bookshop.dto.CategoryResponseDto;
import com.example.bookshop.service.BookService;
import com.example.bookshop.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
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

@Tag(name = "category manager", description = "Endpoints for managing categories")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final BookService bookService;

    @GetMapping
    @Operation(summary = "Find all categories",
            description = "This method finds all categories in store")
    public List<CategoryResponseDto> findAll(Pageable pageable) {
        return categoryService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a category by id",
            description = "This method finds a category by given id")
    public CategoryResponseDto getById(@CategoryIdParameter @PathVariable Long id) {
        return categoryService.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Save a book", description = "This method add a new category to DB")
    public CategoryResponseDto save(@Parameter(
            name = "requestDto",
            description = "This is a request DTO of a category",
            example = """
                    {
                      "name": "Fiction",
                      "description": "Fiction books"
                    }
                   """,
            required = true) @RequestBody @Valid CategoryRequestDto requestDto) {
        return categoryService.save(requestDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a category",
            description = "This method soft-deletes a category from DB")
    public void delete(@CategoryIdParameter @PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Update a category", description = "This method updates a category in DB")
    public CategoryResponseDto update(@CategoryIdParameter @PathVariable Long id,
                                      @RequestBody @Valid CategoryRequestDto requestDto) {
        return categoryService.update(id, requestDto);
    }

    @GetMapping("/{id}/books")
    @Operation(summary = "Find all books by category",
            description = "This method finds all books by it`s category")
    public List<BookDto> findAllBooksByCategory(@CategoryIdParameter @PathVariable Long id,
                                                Pageable pageable) {
        return bookService.findAllByCategory(pageable, id);
    }
}
