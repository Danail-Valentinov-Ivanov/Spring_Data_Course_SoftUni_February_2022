package com.example.springintro;

import com.example.springintro.model.entity.Author;
import com.example.springintro.model.entity.Book;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();
        Scanner scanner = new Scanner(System.in);

//        10 Problem
        this.authorService.getWithTotalCopies()
                .forEach(a -> System.out.println(
                        a.getFirstName() + " " + a.getLastName() +
                                " - " + a.getTotalCopies()));

//        09 Problem
//        int inputLength = Integer.parseInt(scanner.nextLine());
//        System.out.printf("Count books: %d\n", bookService.countBooksWithTitleSize(inputLength));

//        08 Problem
//        String inputStr = scanner.nextLine();
//        bookService.booksLastNameStartWith(inputStr)
//                .forEach(book -> System.out.printf("%s (%s %s)\n"
//                        , book.getTitle(), book.getAuthor().getFirstName(), book.getAuthor().getLastName()));

//        07 Problem
//        String inputStr = scanner.nextLine();
//        bookService.booksContainGivenString(inputStr)
//                .forEach(book -> System.out.println(book.getTitle()));

//        06 Problem
//        String endString = scanner.nextLine();
//        authorService.authorsEndWithGivenString(endString)
//                .forEach(author -> System.out.printf("%s %s\n",author.getFirstName(), author.getLastName()));

//        05 Problem
//            String date = scanner.nextLine();
//            bookService.findBooksReleasedBeforeDate(date)
//                    .forEach(book -> System.out.printf("%s %s %.2f\n", book.getTitle(), book.getEditionType()
//                    , book.getPrice()));

//        04 Problem
//        int year = Integer.parseInt(scanner.nextLine());
//        bookService.findBooksNotReleaseInYear(year).forEach(book -> System.out.println(book.getTitle()));

//        03 Problem
//        String[]data = scanner.nextLine().split("\\s+");
//        double lowerValue = Double.parseDouble(data[0]);
//        double upperValue = Double.parseDouble(data[1]);
//        this.bookService.findBooksWhitPriceLowerThanOrUpperThan(lowerValue, upperValue)
//                .forEach(book -> System.out.printf("%s - $%.2f\n", book.getTitle(), book.getPrice()));

//        02 Problem
//        String[]data = scanner.nextLine().split("\\s+");
//        String editionTypeStr = data[0];
//        int copies = Integer.parseInt(data[1]);
//        bookService.findTitlesByEditionTypeAndCopies(editionTypeStr, copies).forEach(System.out::println);

//        01 Problem
//        String ageRestriction = scanner.nextLine();
//        this.bookService.findAllTitleByAgeRestriction(ageRestriction).forEach(System.out::println);

        //printAllBooksAfterYear(2000);
//        printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(1990);
        //   printAllAuthorsAndNumberOfTheirBooks();
//        printALlBooksByAuthorNameOrderByReleaseDate("George", "Powell");

    }

    private void printALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
