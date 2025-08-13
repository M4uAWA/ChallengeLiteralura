# Conversor de Monedas
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



>Esto es declarado dentro del método `getConversionRate`, el cual regresa un dato de tipo `Double` que representa la tasa de cambio, y tiene como parámetros dos datos de tipo `String` los cuales representan la moneda que se desea convertir y la moneda objetivo de la conversión respectivamente.
- La opción de conversión se convierte en un índice.
---
`App.java`
