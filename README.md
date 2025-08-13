# Catalogo de libros Literalura
Este código fue realizado como propuesta al reto presentado en el curso Java Orientado a Objetos de Oracle ONE con Alura LATAM, denominado "Practicando Spring Boot: Challenge Literalura", y permite el almacenamiento de un catálogo de libros extraídos por medio de una API REST en una base de datos local, brindando algunas opciones de consulta.

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

La aplicación comienza en el archivo `LiteraluraApplication.java` en donde tenemos una aplicación Spring Boot convencional, además de contar con dos atributos `@Autowired` de tipo `BookRepository` y `AuthorRepository`, los cuales serán utilizados para almacenar datos de tipo libro y autor en la base de datos, siendo pasados como parámetros en un objeto `Menu` que se encarga de ejecutar el menú.

Dentro de `Menu.java` tenemos algunos atributos que permiten la entrada de datos por medio del teclado, así como la instanciación de las clases `APIConsumer` y `DataConverter`:
>APIConsumer:
>>Es una clase que utiliza el cliente HTTP incluido en java para hacer la petición de un json a la URL de una API (en este caso la de "[gutendex](https://gutendex.com/)").

>DataConverter:
>>Es una clase que extiende un método de una interfaz del mismo nombre (con un prefijo "I"), dicho método es `obtainData`, el cual utiliza un atributo de tipo `ObjectMapper` para guardar los datos de un json en forma de cadena de texto a una clase de tipo `Data`

Volviendo a `Menu.java` tenemos tambien los atributos `repository` y `aRepository`, siendo estos los encargados de almacenar los repositorios recibidos desde `LiteraluraApplication.java`. Y pasando al primer método de la clase tenemos a `executeMenu`, el cual se encarga de mostrar las opciones del menú en pantalla y de redirigir al usuario dependiendo de la opción seleccionada. Mostrando el funcionamiento de cada opción del menú tenemos:

- searchByTitle(): Este es el método principal del programa, con el es posible la búsqueda de un libro por su título en la API de [gutendex](https://gutendex.com/), esto por medio del consumo de API y la conversión de los datos recibidos en clases manejables por el programa de tipo `Data`,`Book` y `Author`. Tambien se encarga de almacenar los objetos en la base de datos por medio del uso de los repositorios mencionados anteriormente.
- listRegisteredBooks(): Imprime una consulta derivada de JPA que contiene los libros registrados en la base de datos.
- listRegisteredAuthors(): Imprime una consulta derivada de JPA que contiene los autores registrados en la base de datos.
- ListLiveAuthorsByYear(): Imprime una consulta derivada de JPA que contiene los autores vivos en un año especificado.
- listBooksByLanguage(): Imprime una consulta derivada de JPA que contiene los libros disponibles en un lenguaje especificado.
- Salir del programa

El programa cuenta con manejo de excepciones y evita cualquier tipo de comportamiento no deseado, facilitando su uso y siendo tolerante ante posibles errores del usuario.
