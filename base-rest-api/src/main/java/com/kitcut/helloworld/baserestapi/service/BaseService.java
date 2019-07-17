package com.kitcut.helloworld.baserestapi.service;

import java.util.List;

public interface BaseService<E, ID> {
    E findById(ID id);

    List<E> findAll();

    void save(E entity);

    void delete(E entity);

    void deleteById(ID id);
//    <REQUEST, RESPONSE> RESPONSE create(REQUEST request);
}
