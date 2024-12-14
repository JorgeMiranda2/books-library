package com.book.book;

import com.book.book.DTO.ApiResponseDto;
import com.book.book.DTO.BookDto;
import com.book.book.Models.Author;
import com.book.book.Models.Book;
import com.book.book.Models.Language;
import com.book.book.Repositories.AuthorRepository;
import com.book.book.Repositories.LanguageRepository;
import com.book.book.Services.ApiService;
import com.book.book.Services.AuthorService;
import com.book.book.Services.BookService;
import com.book.book.Services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

@SpringBootApplication
public class BookApplication implements CommandLineRunner{

	private final ApiService apiService;
	private final BookService bookService;
	private final AuthorService authorService;
	private final LanguageService languageService;

	@Autowired
	public BookApplication(ApiService apiService, BookService bookService, AuthorService authorService, LanguageRepository languageRepository, LanguageService languageService){
        this.apiService = apiService;
        this.bookService = bookService;
        this.authorService = authorService;
        this.languageService = languageService;

    }

	public static void main(String[] args) {

		SpringApplication.run(BookApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		boolean working = true;

		while (working) {
			System.out.println(
					"""
							***********************************
							Elija la acción a través del número
							Opciones:
							1) Buscar libro por titulo (api)
							2) Listar libros registrados (DB)
							3) Listar autores Registrados (DB)
							4) Listar autores vivos en un determinado año
							5) Listar libros por idioma
							0) Salir
							***********************************
							                    
							"""
			);

			Scanner scanner = new Scanner(System.in);
			int opcion = scanner.nextInt();
			scanner.nextLine();

			switch (opcion){
				case 0:
					working = false;
					break;
				case 1:
					System.out.println("Libro a buscar:");
					String query = scanner.nextLine();
					System.out.println("palabra a buscar: "+ query);
					ApiResponseDto apiResult = apiService.searchBooks(query);
					if(apiResult==null){
						break;
					}
					if(apiResult.results().isEmpty()) {
						System.out.println("Libro no encontrado.");
						break;
					}
					if(apiResult.results().size()>1){
						System.out.println("Más de un libro encontrado, se tomará el primero, si busca uno diferente, por favor especificar más");
						System.out.println(apiResult.results());
					}else{
						System.out.println(apiResult.results());
					}
				Boolean saved = bookService.saveBookFromApi(apiResult);

					break;
				case 2:
					System.out.println("Libros Registrados: ");
					List<Book> books = bookService.getAllBooks();
					for (Book book : books) {
						bookService.showBookOnConsole(book);
					}
					break;
				case 3:
					List<Author> authors = authorService.getAllAuthors();
					System.out.println("Autores: ");
					for (Author author : authors) {
						authorService.showAuthorOnConsole(author);
					}
					break;
				case 4:
					System.out.println("Autores por año");
					System.out.println("Seleccione el año: ");
					Long year = scanner.nextLong();
					Optional<List<Author>> authorsByYear = authorService.getLivingAuthorsOnDate(year);
					if(authorsByYear.isEmpty()){
						System.out.println("No se encuentra autores que hayan vivido en ese año.");
						break;
					}
					System.out.println("Autores encontrados: ");
					for (Author author : authorsByYear.get()) {
						authorService.showAuthorOnConsole(author);
					}
					break;
				case 5:
					System.out.println("Listar libros por idioma: ");
					System.out.println("Libros disponibles: ");
					List<Language> languages = languageService.getAllLanguages();
					List<String> acronyms = languages.stream().map(Language::getAcronym).collect(Collectors.toList());
					System.out.println("Idiomas disponibles:");
					acronyms.forEach(acronym -> System.out.println(acronym));
					System.out.println("Seleccione la nomenclatura correspondiente");
					String searchAcronym = scanner.nextLine();
					Optional<List<Book>> booksByLanguage = bookService.findByLanguage(searchAcronym);
					if(booksByLanguage.isEmpty()){
						System.out.println("no se han encontrado libros con la nomenclatura especificada.");
						break;
					}
					System.out.println("Libros encontrados: ");
					booksByLanguage.get().forEach(book -> bookService.showBookOnConsole(book));
					break;

				default:
					System.out.println("Opción no valida");
					break;
			}


		}
	}
}