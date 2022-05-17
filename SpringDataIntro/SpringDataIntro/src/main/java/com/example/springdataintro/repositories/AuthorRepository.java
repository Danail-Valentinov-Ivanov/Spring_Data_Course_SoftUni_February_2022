package com.example.springdataintro.repositories;

import com.example.springdataintro.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> findByBooksReleaseDateBefore(LocalDate releaseDate);
}
