package com.kitcut.helloworld.springboot.controller;

import com.kitcut.helloworld.springboot.elasticsearch.document.EsBookDocument;
import com.kitcut.helloworld.springboot.elasticsearch.service.EsBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/es-book")
public class EsBookController {

    @Autowired
    EsBookService esBookService;

    @GetMapping("/get-all")
    @CrossOrigin
    public Iterable<EsBookDocument> getAll() {
        return esBookService.findAll();
    }

    @PostMapping("")
    public EsBookDocument create(@RequestBody @Valid EsBookDocument esBookDocument) {
        return esBookService.save(esBookDocument);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody @Valid EsBookDocument esBookDocument) {
        esBookService.save(esBookDocument);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        esBookService.deleteById(id);
    }

    @GetMapping("/{id}")
    public EsBookDocument getDetail(@PathVariable String id) {
        return esBookService.findOne(id);
    }
}
