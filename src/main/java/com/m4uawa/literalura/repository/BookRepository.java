package com.m4uawa.literalura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m4uawa.literalura.model.Book;

public interface BookRepository extends JpaRepository<Book,Long> {
    
}
