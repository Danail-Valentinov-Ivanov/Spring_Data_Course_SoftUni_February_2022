package com.example.springintro.repository;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    List<Book> findAllBookByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    List<Book> findBooksByPriceLessThanOrPriceGreaterThan(BigDecimal lowerValuePrice, BigDecimal upperValuePrice);

    List<Book> findBooksByReleaseDateBeforeOrReleaseDateAfter(LocalDate dateBefore, LocalDate dateAfter);

    List<Book> findBooksByReleaseDateBefore(LocalDate dateBefore);

    List<Book> findBooksByTitleContaining(String inputStr);

    List<Book> findBooksByAuthorLastNameStartingWith(String inputStr);

    @Query("select count(b.id) from Book b where length(b.title) > :lengthWord")
    int countBooks(int lengthWord);


}
