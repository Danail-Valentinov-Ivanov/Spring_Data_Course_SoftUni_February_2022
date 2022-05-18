package com.example.springintro.service;

import com.example.springintro.model.entity.Author;
import com.example.springintro.model.entity.AuthorNamesWithTotalCopies;

import java.io.IOException;
import java.util.List;

public interface AuthorService {


    List<AuthorNamesWithTotalCopies> getWithTotalCopies();

    List<Author> authorsEndWithGivenString(String endString);

    void seedAuthors() throws IOException;

    Author getRandomAuthor();

    List<String> getAllAuthorsOrderByCountOfTheirBooks();
}
