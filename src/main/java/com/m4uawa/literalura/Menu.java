package com.m4uawa.literalura;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.dao.DataIntegrityViolationException;

import com.m4uawa.literalura.model.Author;
import com.m4uawa.literalura.model.Book;
import com.m4uawa.literalura.model.Data;
import com.m4uawa.literalura.repository.AuthorRepository;
import com.m4uawa.literalura.repository.BookRepository;
import com.m4uawa.literalura.service.APIConsumer;
import com.m4uawa.literalura.service.DataConverter;

public class Menu {
    private Scanner keyboard = new Scanner(System.in);
    private APIConsumer apiConsumer = new APIConsumer();
    private DataConverter dataConverter = new DataConverter();
    private final String BASE_URL = "https://gutendex.com/books";

    String menu = """
            \n\nElija la opción a través de su número:
            1- buscar libro por titulo
            2- listar libros registrados
            3- listar autores registrados
            4- listar autores vivos en un determinado año
            5- listar libros por idioma
            0- salir\n\n
            """;

    private BookRepository repository;
    private AuthorRepository aRepository;

    public Menu(BookRepository repository, AuthorRepository aRepository) {
        this.repository = repository;
        this.aRepository = aRepository;
    }

    public void executeMenu(){
        int opt;
        while(true){
            System.out.print(menu);
            System.out.print("Opción: ");
            opt = keyboard.nextInt();
            keyboard.nextLine();

            switch (opt){
                case 0 -> {return;}
                case 1 -> searchByTitle();
                case 2 -> listRegisteredBooks();
                case 3 -> listRegisteredAuthors();
                case 4 -> ListLiveAuthorsByYear();
                case 5 -> listBooksByLanguage();
                default -> System.out.print("Ingresar opcion valida");
            }
        }
        
    }

    private void searchByTitle() {
        System.out.print("Escribe el titulo del libro que deseas buscar: ");
        var bookName = keyboard.nextLine();
        var json = apiConsumer.obtainData(BASE_URL + "/?search=" + bookName.replace(" ", "%20"));
        var dataFound = dataConverter.obtainData(json, Data.class);
        Optional<Book> bookFound = dataFound.getResults().stream().filter(b -> b.getTitle().toUpperCase().contains(bookName.toUpperCase())).findFirst();
        if(bookFound.isPresent()){
            Author author;
            String authorName = bookFound.get().getAuthors().get(0).getName();
            Optional<Author> authorAlreadyExists = aRepository.findByNameIgnoreCase(authorName);
            if (authorAlreadyExists.isPresent()) {
                author = authorAlreadyExists.get();
            } else {
                Author newAuthor = bookFound.get().getAuthors().get(0);
                newAuthor.setId(null);
                author = aRepository.save(newAuthor);
            }
            bookFound.get().setAuthor(author);
            bookFound.get().setLanguage(bookFound.get().getLanguages().get(0));
            bookFound.get().setId(null);
            System.out.print("\n"+bookFound.get());
            try {
                repository.save(bookFound.get());
            } catch (DataIntegrityViolationException e) {
                System.out.println("El libro '" + bookFound.get().getTitle() + "' ya está registrado en la base de datos.");
            }
        } else {
            System.out.print("\nLibro no encontrado");
        }
    }

    private void listRegisteredBooks() {
        List<Book> books = repository.findAllByOrderByTitleAsc();
        if (books.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            books.forEach(System.out::println);
        }
    }
    
    private void listRegisteredAuthors() {
        List<Author> authors = aRepository.findAllByOrderByNameAsc();
        if (authors.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            authors.forEach(System.out::println);
        }
    }

    private void ListLiveAuthorsByYear() {
        
        System.out.print("Ingrese el año: ");
        var input = keyboard.nextLine();
        try {
            int year = Integer.parseInt(input);
            List<Author> authors = aRepository.findByBirthDateLessThanAndDeathDateGreaterThan(year, year);
        
            if (authors.isEmpty()) {
                System.out.println("No hay autores vivos en " + year);
            } else {
                authors.forEach(System.out::println);
            }          
        } catch (NumberFormatException e) {
            System.out.println("'" + input + "' no es un número válido.");
        }

    }

    private void listBooksByLanguage() {
        System.out.print("Ingrese el idioma (ej. en = Inglés, es = Español, fr = Francés, pt = Portugués): ");
        String lang = keyboard.nextLine();
        List<Book> books = repository.findByLanguageIgnoreCase(lang);
        if (books.isEmpty()) {
            System.out.println("No hay libros registrados en el idioma " + lang);
        } else {
            books.forEach(System.out::println);
        }
    }
}