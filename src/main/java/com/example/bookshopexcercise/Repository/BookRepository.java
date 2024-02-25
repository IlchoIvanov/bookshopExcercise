package com.example.bookshopexcercise.Repository;

import com.example.bookshopexcercise.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
