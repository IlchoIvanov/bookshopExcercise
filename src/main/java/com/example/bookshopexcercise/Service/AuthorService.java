package com.example.bookshopexcercise.Service;

import com.example.bookshopexcercise.model.entity.Author;

import java.io.IOException;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();
}
