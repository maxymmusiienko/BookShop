package com.example.bookshop;

import com.example.bookshop.model.Book;
import com.example.bookshop.service.BookService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookShopApplication {
    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(BookShopApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Book book1 = new Book();
            book1.setAuthor("Max");
            book1.setCoverImage("cover1");
            book1.setDescription("good");
            book1.setIsbn("k");
            book1.setPrice(BigDecimal.valueOf(5.99));
            book1.setTitle("jaav");

            Book book2 = new Book();
            book2.setAuthor("Olya");
            book2.setCoverImage("cover2");
            book2.setDescription("goood");
            book2.setIsbn("kj");
            book2.setPrice(BigDecimal.valueOf(8.99));
            book2.setTitle("fgggg");

            bookService.save(book1);
            bookService.save(book2);

            System.out.println(bookService.findAll());
        };
    }
}
