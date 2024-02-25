package com.example.bookshopexcercise.Service.Impl;

import com.example.bookshopexcercise.Repository.AuthorRepository;
import com.example.bookshopexcercise.Service.AuthorService;
import com.example.bookshopexcercise.model.entity.Author;
import com.example.bookshopexcercise.model.entity.Category;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthors() throws IOException {
        if(this.authorRepository.count()>0){
            return;
        }
        List<String[]> authorsFullName = Files.readAllLines(Path.of("src/main/resources/files/authors.txt")).stream().map(row -> row.split("\\s+")).collect(Collectors.toList());
        for (String[] fullName : authorsFullName) {
            Author author = new Author(fullName[0], fullName[1]);
            this.authorRepository.save(author);
        }

    }

    @Override
    public Author getRandomAuthor() {
        long randomId = ThreadLocalRandom.current().nextLong(1, this.authorRepository.count()+1);
        Author author = this.authorRepository.findById(randomId).orElse(null);
        return author;
    }
}
