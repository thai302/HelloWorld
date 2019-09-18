package com.kitcut.helloworld.springboot.elasticsearch.service;

import com.kitcut.helloworld.springboot.elasticsearch.document.EsBookDocument;
import com.kitcut.helloworld.springboot.elasticsearch.repository.EsBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EsBookServiceImpl implements EsBookService {

    @Autowired
    private EsBookRepository esBookRepository;

    @Autowired
    public void setEsBookRepository(EsBookRepository esBookRepository) {
        this.esBookRepository = esBookRepository;
    }

    public EsBookDocument save(EsBookDocument book) {
        return esBookRepository.save(book);
    }

    public void delete(EsBookDocument esBookDocument) {
        esBookRepository.delete(esBookDocument);
    }

    @Override
    public void deleteById(String id) {
        esBookRepository.deleteById(id);
    }

    public EsBookDocument findOne(String id) {
        return esBookRepository.findById(id).get();
    }

    public Iterable<EsBookDocument> findAll() {
        return esBookRepository.findAll();
    }

    public Page<EsBookDocument> findByAuthor(String author, PageRequest pageRequest) {
        return esBookRepository.findByAuthor(author, pageRequest);
    }

    public List<EsBookDocument> findByTitle(String title) {
        return esBookRepository.findByTitle(title);
    }

}