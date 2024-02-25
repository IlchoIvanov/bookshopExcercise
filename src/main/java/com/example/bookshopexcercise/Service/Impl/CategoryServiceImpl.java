package com.example.bookshopexcercise.Service.Impl;

import com.example.bookshopexcercise.Repository.CategoryRepository;
import com.example.bookshopexcercise.Service.CategoryService;
import com.example.bookshopexcercise.model.entity.Category;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategories() throws IOException {
        if(this.categoryRepository.count()>0){
            return;
        }
        Files.readAllLines(Path.of("src/main/resources/files/categories.txt")).stream().filter(row-> !row.isBlank()).map(row->new Category(row)).forEach(this.categoryRepository::save);

    }

    @Override
    public Set<Category> getRandomCategories() {
        Set<Category> categories = new HashSet<>();
        long setCount = ThreadLocalRandom.current().nextLong(1, 4);
        for (int i = 0; i < setCount; i++) {
            long randomId = ThreadLocalRandom.current().nextLong(1, this.categoryRepository.count()+1);
            categories.add(this.categoryRepository.findById(randomId).orElse(null));
        }
        return categories;
    }
}
