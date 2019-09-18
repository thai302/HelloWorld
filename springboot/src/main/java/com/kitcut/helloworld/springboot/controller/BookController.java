package com.kitcut.helloworld.springboot.controller;

import com.kitcut.helloworld.springboot.entity.BookEntity;
import com.kitcut.helloworld.springboot.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/get-all")
    @CrossOrigin
    public Iterable<BookEntity> getAll() {
        return bookService.findAll();
    }

    @PostMapping("")
    public BookEntity create(@RequestBody @Valid BookEntity BookEntity) {
        return bookService.save(BookEntity);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody @Valid BookEntity BookEntity) {
        bookService.save(BookEntity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        bookService.deleteById(id);
    }

    @GetMapping("/{id}")
    public BookEntity getDetail(@PathVariable String id) {
        return bookService.findOne(id);
    }
}
