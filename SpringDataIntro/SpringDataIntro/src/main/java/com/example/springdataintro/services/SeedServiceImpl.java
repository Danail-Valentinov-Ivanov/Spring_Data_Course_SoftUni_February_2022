package com.example.springdataintro.services;

import com.example.springdataintro.entities.*;
import com.example.springdataintro.repositories.AuthorRepository;
import com.example.springdataintro.repositories.BookRepository;
import com.example.springdataintro.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {
    private static final String RESOURCE_PATH = "src/main/resources/files/";
    private static final String AUTHORS_FILE_PATH = RESOURCE_PATH + "authors.txt";
    private static final String CATEGORIES_FILE_PATH = RESOURCE_PATH + "categories.txt";
    private static final String BOOKS_FILE_PATH = RESOURCE_PATH + "books.txt";

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private final BookRepository bookRepository;

    @Autowired
    public SeedServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    private AuthorService authorService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public void seedAuthors() throws IOException {
        Files.readAllLines(Path.of(AUTHORS_FILE_PATH))
                .stream()
                .filter(row -> !row.isBlank())
                .map(row -> row.split("\\s+"))
                .map(name -> new Author(name[0], name[1]))
                .forEach(authorRepository::save);
    }

    @Override
    public void seedCategories() throws IOException {
        Files.readAllLines(Path.of(CATEGORIES_FILE_PATH))
                .stream()
                .filter(row -> !row.isBlank())
                .map(name -> new Category(name))
                .forEach(categoryRepository::save);
    }

    @Override
    public void seedBooks() throws IOException {
        Files.readAllLines(Path.of(BOOKS_FILE_PATH))
                .stream()
                .filter(row -> !row.isBlank())
                .map(row -> getBookObject(row))
                .forEach(bookRepository::save);
    }

    private Book getBookObject(String row) {
        String[] bookData = row.split("\\s+");

        int indexEnum = Integer.parseInt(bookData[0]);
        EditionType editionType = EditionType.values()[indexEnum];

        LocalDate releaseDate = LocalDate.parse(bookData[1], DateTimeFormatter.ofPattern("d/M/yyyy"));

        int copies = Integer.parseInt(bookData[2]);

        BigDecimal price = new BigDecimal(bookData[3]);

        int indexEnumAgeRestriction = Integer.parseInt(bookData[4]);
        AgeRestriction ageRestriction = AgeRestriction.values()[indexEnumAgeRestriction];

        String title = Arrays.stream(bookData).skip(5).collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();

        Set<Category> categories = categoryService.getRandomCategories();

        return new Book(title, editionType, price, copies, releaseDate, ageRestriction, author, categories);
    }
}
