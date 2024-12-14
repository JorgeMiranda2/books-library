package com.book.book.Services;

import com.book.book.DTO.ApiResponseDto;
import com.book.book.DTO.BookDto;
import com.book.book.Models.Author;
import com.book.book.Models.Book;
import com.book.book.Models.Language;
import com.book.book.Repositories.AuthorRepository;
import com.book.book.Repositories.BookRepository;
import com.book.book.Repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final LanguageRepository languageRepository;
    private final AuthorRepository authorRepository;
    @Autowired
    public BookService(BookRepository bookRepository, LanguageRepository languageRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.languageRepository = languageRepository;
        this.authorRepository = authorRepository;
    }

    @Transactional
    public Boolean saveBookFromApi(ApiResponseDto apiResponseDto) {
        // Obtener el libro desde la API
        BookDto bookDto = apiResponseDto.results().get(0);

        // Verificar si el libro ya existe por título
        if (bookRepository.existsByTitle(bookDto.title())) {
            System.out.println("El libro ya se encuentra registrado en la base de datos");
            return false; // Salir si el libro ya está registrado
        }

        // Buscar o crear lenguajes
        Set<Language> languages = bookDto.languages().stream()
                .map(language -> languageRepository.findByAcronym(language).orElseGet(() -> {
                    // Crear nuevo lenguaje si no existe
                    Language newLanguage = Language.builder()
                            .acronym(language)
                            .build();
                    return languageRepository.save(newLanguage);
                }))
                .collect(Collectors.toSet());

        System.out.println("Lenguaje guardado: " + languages);

        // Buscar o crear autores
        Set<Author> authors = bookDto.author().stream()
                .map(authorDto -> authorRepository.findByName(authorDto.name())
                        .orElseGet(() -> {
                            // Crear nuevo autor si no existe
                            Author newAuthor = Author.builder()
                                    .name(authorDto.name())
                                    .birthYear(authorDto.birthYear())
                                    .deathYear(authorDto.deathYear())
                                    .build();
                            return authorRepository.save(newAuthor);
                        }))
                .collect(Collectors.toSet());

        System.out.println("Guardando Libro");
        System.out.println("lenguajes: "  + languages.stream().map(language -> Language.builder().id(language.getId()).build()).collect(Collectors.toList()));

        // Crear y relacionar el libro
        Book book = Book.builder()
                .title(bookDto.title())
                .languages(languages) // Relación con lenguajes
                .authors(authors)     // Relación con autores
                .downloads(bookDto.downloads())
                .build();

        System.out.println("libro: " + book);

        // Guardar el libro
        bookRepository.save(book);

        System.out.println("Libro guardado exitosamente: " + book.getTitle());

        return true;
    }


    public void showBookOnConsole(Book book){
        System.out.println("======================= LIBRO =======================");
        System.out.println("Titulo: " + book.getTitle());
        System.out.println("Autores: " + book.getAuthors().stream().map(Author::getName).collect(Collectors.toSet()));
        System.out.println("Idiomas: " + book.getLanguages().stream().map(Language::getAcronym).collect(Collectors.toSet()));
        System.out.println("Descargas: "+ book.getDownloads());
        System.out.println("=====================================================");
    }

    @Transactional
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }


    public Optional<List<Book>> findByLanguage(String searchAcronym) {
        return bookRepository.findBooksByLanguage(searchAcronym);
    }
}
