package com.example.bookshopexcercise.Repository;

import com.example.bookshopexcercise.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
