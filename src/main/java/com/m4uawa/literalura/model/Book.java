package com.m4uawa.literalura.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@JsonIgnoreProperties(ignoreUnknown=true)
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    @Column(unique=true)
    @JsonAlias("title") String title;
    
    @Transient
    @JsonAlias("authors") List<Author> authors;

    @ManyToOne(fetch=FetchType.EAGER)
    Author author;

    @Transient
    @JsonAlias("languages") List<String> languages;

    String language;

    @JsonAlias("download_count") Double numberOfDownloads;

    @Override
    public String toString() {
        return "Book [titulo=" + title + ", author=" + author + ", lenguage=" + language + ", numberOfDownloads="
                + numberOfDownloads + "]";
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Double getNumberOfDownloads() {
        return numberOfDownloads;
    }

    public void setNumberOfDownloads(Double numberOfDownloads) {
        this.numberOfDownloads = numberOfDownloads;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    
}
