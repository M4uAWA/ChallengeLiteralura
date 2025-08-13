package com.m4uawa.literalura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m4uawa.literalura.model.Book;

public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findAllByOrderByTitleAsc();
    List<Book> findByLanguageIgnoreCase(String language);
    List<Book> findByAuthor_NameIgnoreCase(String name);
}
