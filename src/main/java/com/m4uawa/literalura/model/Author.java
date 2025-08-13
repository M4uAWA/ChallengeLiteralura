package com.m4uawa.literalura.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@JsonIgnoreProperties(ignoreUnknown=true)
@Entity
@Table(name = "author")
public class Author {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique=true)
    @JsonAlias("name") private String name;

    @JsonAlias("birth_year") private int birthDate;

    @JsonAlias("death_year") private int deathDate;

    @OneToMany(mappedBy= "author",fetch=FetchType.EAGER)
    private List<Book> books;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(int birthDate) {
        this.birthDate = birthDate;
    }

    public int getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(int deathDate) {
        this.deathDate = deathDate;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        books.forEach(b -> b.setAuthor(this));
        this.books = books;
    }

    @Override
    public String toString() {
        List<String> booksAtName = new ArrayList<>();
        this.books.forEach(b->booksAtName.add(b.getTitle()));
        return "\nAUTOR\nNombre: " + name + "\nFecha de Nacimiento:" + birthDate + "\nFecha de Fallecimiento: " + deathDate + "\nLibros: "+booksAtName;
    }
    
}
