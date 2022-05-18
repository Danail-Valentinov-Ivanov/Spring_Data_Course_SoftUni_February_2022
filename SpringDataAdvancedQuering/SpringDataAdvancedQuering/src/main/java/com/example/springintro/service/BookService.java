package com.example.springintro.service;

import com.example.springintro.model.entity.Author;
import com.example.springintro.model.entity.Book;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);



    int countBooksWithTitleSize(int longSymbol);

    List<Book> booksLastNameStartWith(String inputStr);

    List<Book> booksContainGivenString(String inputStr);

    List<Book> findBooksReleasedBeforeDate(String date);

    List<Book> findBooksNotReleaseInYear(int year);

    List<Book> findBooksWhitPriceLowerThanOrUpperThan(double lowerValue, double upperValue);

    List<String > findTitlesByEditionTypeAndCopies(String editionTypeStr, int copies);

    List<String> findAllTitleByAgeRestriction(String ageRestriction);


}
