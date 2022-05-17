package com.example.springdataintro.services;

import com.example.springdataintro.entities.Author;
import com.example.springdataintro.entities.Category;
import com.example.springdataintro.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> getRandomCategories() {
        long size = categoryRepository.count();
        Random random = new Random();
        Set<Integer>categoriesIds = new HashSet<>();
        for (int i = 0; i < size; i++) {
            int nextId = random.nextInt((int) size) + 1;
            categoriesIds.add(nextId);
        }
        List<Category> categoriesById = categoryRepository.findAllById(categoriesIds);
        return new HashSet<>(categoriesById);
    }
}
