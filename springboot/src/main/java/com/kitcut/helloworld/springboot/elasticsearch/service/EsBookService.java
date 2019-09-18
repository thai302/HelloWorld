package com.kitcut.helloworld.springboot.elasticsearch.service;

import com.kitcut.helloworld.springboot.elasticsearch.document.EsBookDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface EsBookService {

    EsBookDocument save(EsBookDocument esBookDocument);

    void delete(EsBookDocument esBookDocument);

    void deleteById(String id);

    EsBookDocument findOne(String id);

    Iterable<EsBookDocument> findAll();

    Page<EsBookDocument> findByAuthor(String author, PageRequest pageRequest);

    List<EsBookDocument> findByTitle(String title);

}