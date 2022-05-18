package com.example.springintro.service.impl;

import com.example.springintro.model.entity.*;
import com.example.springintro.repository.BookRepository;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final String BOOKS_FILE_PATH = "src/main/resources/files/books.txt";

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }





    @Override
    public int countBooksWithTitleSize(int lengthWord){
        return bookRepository.countBooks(lengthWord);
    }

    @Override
    public List<Book> booksLastNameStartWith(String inputStr){
        return bookRepository.findBooksByAuthorLastNameStartingWith(inputStr);
    }

    @Override
    public List<Book> booksContainGivenString(String inputStr){
        return bookRepository.findBooksByTitleContaining(inputStr);
    }

    @Override
    public List<Book> findBooksReleasedBeforeDate(String date){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateBefore = LocalDate.parse(date, dateTimeFormatter);
        return bookRepository.findBooksByReleaseDateBefore(dateBefore);
    }

    @Override
    public List<Book> findBooksNotReleaseInYear(int year){
        LocalDate dateBefore = LocalDate.of(year, 1,1);
        LocalDate dateAfter = LocalDate.of(year, 12,31);
        return bookRepository.findBooksByReleaseDateBeforeOrReleaseDateAfter(dateBefore, dateAfter);
    }

    @Override
    public List<Book> findBooksWhitPriceLowerThanOrUpperThan(double lowerValue, double upperValue) {
        BigDecimal lowerValuePrice = BigDecimal.valueOf(lowerValue);
        BigDecimal upperValuePrice = BigDecimal.valueOf(upperValue);
        return this.bookRepository.findBooksByPriceLessThanOrPriceGreaterThan(lowerValuePrice, upperValuePrice);
    }

    @Override
    public List<String> findTitlesByEditionTypeAndCopies(String editionTypeStr, int copies) {
        EditionType editionType = EditionType.valueOf(editionTypeStr.toUpperCase());
        List<Book> books = this.bookRepository.findByEditionTypeAndCopiesLessThan(editionType, copies);
        return books.stream().map(Book::getTitle).collect(Collectors.toList());
    }

    @Override
    public List<String> findAllTitleByAgeRestriction(String ageRestrictionStr) {
        AgeRestriction ageRestriction = AgeRestriction.valueOf(ageRestrictionStr.toUpperCase());
        List<Book> books = bookRepository.findAllBookByAgeRestriction(ageRestriction);
        return books.stream().map(Book::getTitle).collect(Collectors.toList());
    }

    @Override
    public void seedBooks() throws IOException {
        if (bookRepository.count() > 0) {
            return;
        }

        Files
                .readAllLines(Path.of(BOOKS_FILE_PATH))
                .forEach(row -> {
                    String[] bookInfo = row.split("\\s+");

                    Book book = createBookFromInfo(bookInfo);

                    bookRepository.save(book);
                });
    }

    @Override
    public List<Book> findAllBooksAfterYear(int year) {
        return bookRepository
                .findAllByReleaseDateAfter(LocalDate.of(year, 12, 31));
    }

    @Override
    public List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year) {
        return bookRepository
                .findAllByReleaseDateBefore(LocalDate.of(year, 1, 1))
                .stream()
                .map(book -> String.format("%s %s", book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName()))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName) {
        return bookRepository
                .findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(firstName, lastName)
                .stream()
                .map(book -> String.format("%s %s %d",
                        book.getTitle(),
                        book.getReleaseDate(),
                        book.getCopies()))
                .collect(Collectors.toList());
    }

    private Book createBookFromInfo(String[] bookInfo) {
        EditionType editionType = EditionType.values()[Integer.parseInt(bookInfo[0])];
        LocalDate releaseDate = LocalDate
                .parse(bookInfo[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        Integer copies = Integer.parseInt(bookInfo[2]);
        BigDecimal price = new BigDecimal(bookInfo[3]);
        AgeRestriction ageRestriction = AgeRestriction
                .values()[Integer.parseInt(bookInfo[4])];
        String title = Arrays.stream(bookInfo)
                .skip(5)
                .collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();
        Set<Category> categories = categoryService
                .getRandomCategories();

        return new Book(editionType, releaseDate, copies, price, ageRestriction, title, author, categories);

    }
}
