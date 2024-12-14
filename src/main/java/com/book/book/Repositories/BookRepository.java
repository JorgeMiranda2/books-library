package com.book.book.Repositories;

import com.book.book.Models.Author;
import com.book.book.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {

    boolean existsByTitle(String title);

    @Query("SELECT b FROM Book b JOIN b.languages l WHERE LOWER(l.acronym) = LOWER(?1)")
    Optional<List<Book>> findBooksByLanguage(String language);
}
