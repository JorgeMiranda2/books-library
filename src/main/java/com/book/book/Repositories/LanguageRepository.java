package com.book.book.Repositories;

import com.book.book.Models.Author;
import com.book.book.Models.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language,Long> {
    Optional<Language> findByAcronym(String name);
}
