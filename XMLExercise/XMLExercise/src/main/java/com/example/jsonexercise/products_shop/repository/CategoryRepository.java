package com.example.jsonexercise.products_shop.repository;

import com.example.jsonexercise.products_shop.entity.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
