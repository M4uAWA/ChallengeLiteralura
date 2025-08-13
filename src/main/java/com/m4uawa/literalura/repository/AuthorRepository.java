package com.m4uawa.literalura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.m4uawa.literalura.model.Author;

public interface AuthorRepository extends JpaRepository<Author,Long> {
    Optional<Author> findByNameIgnoreCase(String name);

    List<Author> findAllByOrderByNameAsc();
    List<Author> findByBirthDateBeforeAndDeathDateAfter(int start, int end);
}
