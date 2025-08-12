package com.m4uawa.literalura.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Book {
    @JsonAlias("title") String title;

    @JsonAlias("authors") List<Author> author;

    @JsonAlias("languages") List<String> languages;

    @JsonAlias("download_count") Double numberOfDownloads;

    @Override
    public String toString() {
        return "Book [titulo=" + title + ", author=" + author + ", lenguages=" + languages + ", numberOfDownloads="
                + numberOfDownloads + "]";
    }

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
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
    
}
