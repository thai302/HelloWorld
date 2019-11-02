package com.kitcut.helloworld.baserestapi.service.impl;

import com.kitcut.helloworld.baserestapi.exception.NotFoundException;
import com.kitcut.helloworld.baserestapi.service.BaseService;
import com.kitcut.helloworld.baserestapi.util.MessageSourceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class BaseServiceImpl<E, ID, R extends JpaRepository<E, ID>> implements BaseService<E, ID> {
    @Autowired
    protected R repo;

    private Class<E> clazzEntity;

    @PostConstruct
    void init() {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) type;
        clazzEntity = (Class<E>) paramType.getActualTypeArguments()[0];
    }

    @Override
    public E findById(ID id) {
        if (id == null) {
            String msg = MessageSourceUtils.getMessage("entity.id.empty", clazzEntity.getSimpleName());
            throw new NotFoundException(msg);
        } else {
            try {
                Optional<E> optional = repo.findById(id);
                if (!optional.isPresent()) {
                    String msg = MessageSourceUtils.getMessage("entity.id.not-found", clazzEntity.getSimpleName(), String.valueOf(id));
                    throw new NotFoundException(msg);
                }
                return optional.get();
            } catch (NoSuchElementException e) {
                String msg = MessageSourceUtils.getMessage("entity.id.not-found", clazzEntity.getSimpleName(), String.valueOf(id));
                throw new NotFoundException(msg);
            }
        }
    }

    @Override
    public List<E> findAll() {
        return repo.findAll();
    }

    @Override
    public Page<E> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public void save(E entity) {
        repo.save(entity);
    }

    @Override
    public void delete(E entity) {
        repo.delete(entity);
    }

    @Override
    public void deleteById(ID id) {
        E entity = findById(id);
        delete(entity);
    }
}
