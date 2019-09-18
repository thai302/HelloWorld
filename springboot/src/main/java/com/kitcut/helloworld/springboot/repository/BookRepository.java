package com.kitcut.helloworld.springboot.repository;

import com.kitcut.helloworld.springboot.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, String> {

    Page<BookEntity> findByAuthor(String author, Pageable pageable);

    List<BookEntity> findByTitle(String title);

}