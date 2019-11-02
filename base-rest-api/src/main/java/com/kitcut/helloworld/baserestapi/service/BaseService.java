package com.kitcut.helloworld.baserestapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseService<E, ID> {
    E findById(ID id);

    List<E> findAll();

    Page<E> findAll(Pageable pageable);

    void save(E entity);

    void delete(E entity);

    void deleteById(ID id);
//    <REQUEST, RESPONSE> RESPONSE create(REQUEST request);
}
