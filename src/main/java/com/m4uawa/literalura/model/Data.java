package com.m4uawa.literalura.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Data {
    @JsonAlias("results") List<Book> results;

    public List<Book> getResults() {
        return results;
    }

    public void setResults(List<Book> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Data [results=" + results + "]";
    }
}
