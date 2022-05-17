package com.example.springdataintro.services;

import com.example.springdataintro.entities.Author;
import com.example.springdataintro.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getRandomAuthor() {
        long size = authorRepository.count();
        int authorId = new  Random().nextInt((int) size) + 1;
        Author author = authorRepository.findById(authorId).get();
        return author;
    }
}
