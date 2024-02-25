package com.example.bookshopexcercise.Service;

import com.example.bookshopexcercise.model.entity.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {
    void seedCategories() throws IOException;

    Set<Category> getRandomCategories();
}
