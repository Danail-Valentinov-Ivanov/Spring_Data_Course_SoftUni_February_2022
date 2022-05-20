package com.example.jsonexercise.products_shop.repository;

import com.example.jsonexercise.products_shop.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
