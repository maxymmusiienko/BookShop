package com.example.bookshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Entity
@SQLDelete(sql = "UPDATE books SET is_deleted=true WHERE id=?")
@Where(clause = "is_deleted=false")
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private BigDecimal price;

    private String description;

    private String coverImage;

    @Column(nullable = false)
    private boolean isDeleted = false;

    public Book() {
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Book{");
        result.append("id=").append(id).append(", title='").append(title)
                .append('\'').append(", author='").append(author)
                .append('\'').append(", isbn='").append(isbn)
                .append('\'').append(", price=").append(price)
                .append(", description='").append(description)
                .append('\'').append(", coverImage='").append(coverImage)
                .append('\'').append("} ");
        return result.toString();
    }
}
