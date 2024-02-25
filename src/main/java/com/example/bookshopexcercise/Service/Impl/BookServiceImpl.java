package com.example.bookshopexcercise.Service.Impl;

import com.example.bookshopexcercise.Repository.BookRepository;
import com.example.bookshopexcercise.Service.AuthorService;
import com.example.bookshopexcercise.Service.BookService;
import com.example.bookshopexcercise.Service.CategoryService;
import com.example.bookshopexcercise.model.entity.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {
        if(this.bookRepository.count()>0) {
            return;
        }
        List<String> bookInfo = Files.readAllLines(Path.of("src/main/resources/files/books.txt")).stream().collect(Collectors.toList());
        for (String bookData : bookInfo) {
            String[] data = bookData.split("\\s+");
            Author author = authorService.getRandomAuthor();
            EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];
            LocalDate releaseDate = LocalDate.parse(data[1],
                    DateTimeFormatter.ofPattern("d/M/yyyy"));
            int copies = Integer.parseInt(data[2]);
            BigDecimal price = new BigDecimal(data[3]);
            AgeRestriction ageRestriction = AgeRestriction
                    .values()[Integer.parseInt(data[4])];
            String title = Arrays.stream(data)
                    .skip(5)
                    .collect(Collectors.joining(" "));
            Set<Category> categories = categoryService.getRandomCategories();


            Book book = new Book(title, editionType, price, releaseDate, ageRestriction, author, categories, copies);

            bookRepository.save(book);
        }

    }
}
