package com.book.book.Services;

import com.book.book.Models.Author;
import com.book.book.Models.Book;
import com.book.book.Repositories.AuthorRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AuthorService {


    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors(){
        List<Author> authors = authorRepository.findAll();
        authors.forEach(author -> Hibernate.initialize(author.getBooks())); // Inicializar manualmente
        return authors;
    }

    public void showAuthorOnConsole(Author author){
        System.out.println("=====================================================");
        System.out.println("Nombre: " + author.getName());
        System.out.println("Año de nacimiento: " + author.getBirthYear());
        System.out.println("Año de muerte: " + author.getDeathYear());
        System.out.println("Libros: " + author.getBooks().stream().map(Book::getTitle).collect(Collectors.toSet()));
        System.out.println("=====================================================");
    }

    public Optional<List<Author>> getLivingAuthorsOnDate(Long year) {
        return authorRepository.findAuthorsByYear(year);
    }
}
