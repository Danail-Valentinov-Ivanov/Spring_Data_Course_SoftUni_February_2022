package com.example.springdataintro;

import com.example.springdataintro.entities.Author;
import com.example.springdataintro.entities.Book;
import com.example.springdataintro.repositories.AuthorRepository;
import com.example.springdataintro.repositories.BookRepository;
import com.example.springdataintro.services.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public ConsoleRunner(SeedService seedService, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.seedService = seedService;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        this.seedService.seedAuthors();
//        this.seedService.seedCategories();
//        this.seedService.seedAll();

//        _01_booksAfter2000();
//        _02_authorsWithBookBefore1990();
        _03_allAuthorsOrderedByBookCount();
    }

    private void _01_booksAfter2000() {
        LocalDate releaseDate = LocalDate.of(2000, 12, 31);
        List<Book> books = bookRepository.findAllByReleaseDateAfter(releaseDate);
        books.forEach(book -> System.out.println(book.getTitle()));
        System.out.println("Total count books: " + books.size());
    }

    private void _02_authorsWithBookBefore1990() {
        LocalDate releaseDate = LocalDate.of(1990, 1, 1);
        List<Author> authors = authorRepository.findByBooksReleaseDateBefore(releaseDate);
        List<Author> filteredAuthors = authors.stream().filter(author -> author.getBooks().size() >= 1)
                .collect(Collectors.toList());
        filteredAuthors.forEach(author -> System.out.println(author.getFirstName() + " " + author.getLastName()));
        System.out.println("Count authors: " + filteredAuthors.size());
    }

    private void _03_allAuthorsOrderedByBookCount() {
        List<Author> authors = this.authorRepository.findAll();

        authors.stream().sorted((l, r) -> r.getBooks().size() - l.getBooks().size())
                .forEach(author -> System.out.printf("%s %s -> %d%n",
                        author.getFirstName(),
                        author.getLastName(),
                        author.getBooks().size()));
    }
}
