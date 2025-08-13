# Catalogo de libros Literalura
Este código fue realizado como propuesta al reto presentado en el curso Java Orientado a Objetos de Oracle ONE con Alura LATAM, denominado "Practicando Spring Boot: Challenge Literalura", y permite el almacenamiento de un catalogo de libros extraidos por medio de una API REST en una base de datos local, brindando algunas opciones de consulta.

---
El código está realizado en su totalidad con Java (17.0.15) y Spring Boot (3.5.4). La estructura de los archivos es la siguiente:
```
└── ChallengeLiteralura-main/
    ├── .gitattributes
    ├── .gitignore
    ├── mvnw
    ├── mvnw.cmd
    ├── pom.xml
    ├── .mvn/
    │   └── wrapper/
    │       └── maven-wrapper.properties
    └── src/
        ├── main/
        │   ├── resources/
        │   │   └── application.properties
        │   └── java/
        │       └── com/
        │           └── m4uawa/
        │               └── literalura/
        │                   ├── LiteraluraApplication.java
        │                   ├── Principal.java
        │                   ├── service/
        │                   │   ├── APIConsumer.java
        │                   │   ├── DataConverter.java
        │                   │   └── IDataConverter.java
        │                   ├── repository/
        │                   │   ├── AuthorRepository.java
        │                   │   └── BookRepository.java
        │                   └── model/
        │                       ├── Author.java
        │                       ├── Book.java
        │                       └── Data.java
        └── test/
            └── java/
                └── com/
                    └── m4uawa/
                        └── literalura/
                            └── LiteraluraApplicationTests.java

```

La aplicacion comienza en el archivo `LiteraluraApplication.java` en donde tenemos una aplicacion Spring Boot convencional, ademas de contar con dos atributos `@Autowired` de tipo `BookRepository` y `AuthorRepository`, los cuales seran utilizados para almacenar datos de tipo libro y autor en la base de datos, siendo pasados como parametros en un objeto `Menu` que se encarga de ejecutar el menu.

Dentro de `Menu.java` tenemos algunos atributos que permiten la entrada de datos por medio del teclado, asi como la instanciacion de las clases `APIConsumer` y `DataConverter`:
>APIConsumer:
>>Es una clase que utiliza el cliente HTTP incluido en java para hacer la peticion de un json a la URL de una API (en este caso la de "[gutendex](https://gutendex.com/)").

>DataConverter:
>>Es una clase que extiende un metodo de una interfaz del mismo nombre (con un prefijo "I"), dicho metodo es `obtainData`, el cual
