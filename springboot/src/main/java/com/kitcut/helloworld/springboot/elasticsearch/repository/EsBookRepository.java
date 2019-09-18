package com.kitcut.helloworld.springboot.elasticsearch.repository;

import com.kitcut.helloworld.springboot.elasticsearch.document.EsBookDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface EsBookRepository extends ElasticsearchRepository<EsBookDocument, String> {

    Page<EsBookDocument> findByAuthor(String author, Pageable pageable);

    List<EsBookDocument> findByTitle(String title);

}