package com.m4uawa.literalura;

import java.util.Optional;
import java.util.Scanner;

import com.m4uawa.literalura.model.Book;
import com.m4uawa.literalura.model.Data;
import com.m4uawa.literalura.service.APIConsumer;
import com.m4uawa.literalura.service.DataConverter;

public class Principal {
    private Scanner keyboard = new Scanner(System.in);
    private APIConsumer apiConsumer = new APIConsumer();
    private DataConverter dataConverter = new DataConverter();
    private final String BASE_URL = "https://gutendex.com/books?search=";

    String menu = """
            \n\nElija la opción a través de su número:
            1- buscar libro por titulo
            2- listar libros registrados
            3- listar autores registrados
            4- listar autores vivos en un determinado año
            5- listar libros por idioma
            0- salir\n\n
            """;

    public void executeMenu(){
        int opt;
        while(true){
            System.out.print(menu);
            opt = keyboard.nextInt();
            keyboard.nextLine();

            switch (opt){
                case 0:
                    break;
                case 1:
                    searchByTitle();
                case 2:
                    listRegisteredBooks();
                case 3:
                    listRegisteredAuthors();
                case 4:
                    ListLiveAuthorsByYear();
                case 5:
                    listBooksByLanguage();
                default:
                    System.out.print("Ingresar opcion valida");
            }
        }
        
    }

    private void searchByTitle() {
        System.out.print("Escribe el titulo del libro que deseas buscar: ");
        var bookName = keyboard.nextLine();
        var json = apiConsumer.obtainData(BASE_URL + bookName.replace(" ", "%20"));
        var dataFound = dataConverter.obtainData(json, Data.class); // PROBLEMA
        Optional<Book> bookFound = dataFound.getResults().stream().findFirst();
        if(bookFound.isPresent()){
            System.out.print("\n"+bookFound.get());
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
