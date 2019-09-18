package com.kitcut.helloworld.springboot.service.impl;

import com.kitcut.helloworld.springboot.elasticsearch.document.EsBookDocument;
import com.kitcut.helloworld.springboot.elasticsearch.repository.EsBookRepository;
import com.kitcut.helloworld.springboot.elasticsearch.service.EsBookService;
import com.kitcut.helloworld.springboot.entity.BookEntity;
import com.kitcut.helloworld.springboot.repository.BookRepository;
import com.kitcut.helloworld.springboot.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private EsBookService esBookService;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookEntity save(BookEntity book) {
        BookEntity bookEntity = bookRepository.save(book);

        EsBookDocument esBookDocument = new EsBookDocument();
        esBookDocument.setId(bookEntity.getId());
        esBookDocument.setTitle(bookEntity.getTitle());
        esBookDocument.setAuthor(bookEntity.getAuthor());
        esBookDocument.setReleaseDate(bookEntity.getReleaseDate());
        esBookService.save(esBookDocument);

        return bookEntity;
    }

    public void delete(BookEntity book) {
        bookRepository.delete(book);
    }

    @Override
    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }

    public BookEntity findOne(String id) {
        return bookRepository.findById(id).get();
    }

    public Iterable<BookEntity> findAll() {
        return bookRepository.findAll();
    }

    public Page<BookEntity> findByAuthor(String author, PageRequest pageRequest) {
        return bookRepository.findByAuthor(author, pageRequest);
    }

    public List<BookEntity> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

}