package com.m4uawa.literalura;

import java.util.Optional;
import java.util.Scanner;

import com.m4uawa.literalura.model.Author;
import com.m4uawa.literalura.model.Book;
import com.m4uawa.literalura.model.Data;
import com.m4uawa.literalura.repository.AuthorRepository;
import com.m4uawa.literalura.repository.BookRepository;
import com.m4uawa.literalura.service.APIConsumer;
import com.m4uawa.literalura.service.DataConverter;

public class Principal {
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

    public Principal(BookRepository repository, AuthorRepository aRepository) {
        this.repository = repository;
        this.aRepository = aRepository;
    }

    public void executeMenu(){
        int opt;
        while(true){
            System.out.print(menu);
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
            repository.save(bookFound.get());
        } else {
            System.out.print("\nLibro no encontrado");
        }
    }

    private void listRegisteredBooks() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void listRegisteredAuthors() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void ListLiveAuthorsByYear() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void listBooksByLanguage() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
