package com.example.bookshopexcercise;

import com.example.bookshopexcercise.Service.AuthorService;
import com.example.bookshopexcercise.Service.BookService;
import com.example.bookshopexcercise.Service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component

public class ConsoleRunner implements CommandLineRunner {
    private final CategoryService categoryService;
    private final BookService bookService;
    private final AuthorService authorService;

    public ConsoleRunner(CategoryService categoryService, BookService bookService, AuthorService authorService) {
        this.categoryService = categoryService;
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();
    }

    private void seedData() throws IOException {
        this.categoryService.seedCategories();
        this.authorService.seedAuthors();
        this.bookService.seedBooks();

    }
}
