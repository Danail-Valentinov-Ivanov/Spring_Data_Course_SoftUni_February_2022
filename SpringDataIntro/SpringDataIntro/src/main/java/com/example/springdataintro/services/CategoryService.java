package com.example.springdataintro.services;

import com.example.springdataintro.entities.Category;

import java.util.Set;

public interface CategoryService {
    Set<Category> getRandomCategories();
}
