package com.kitcut.helloworld.springboot.service;

import com.kitcut.helloworld.springboot.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface BookService {

    BookEntity save(BookEntity BookEntity);

    void delete(BookEntity BookEntity);

    void deleteById(String id);

    BookEntity findOne(String id);

    Iterable<BookEntity> findAll();

    Page<BookEntity> findByAuthor(String author, PageRequest pageRequest);

    List<BookEntity> findByTitle(String title);

}