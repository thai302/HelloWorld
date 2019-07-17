package com.kitcut.helloworld.baserestapi.service.impl;

import com.kitcut.helloworld.baserestapi.exception.BadRequestException;
import com.kitcut.helloworld.baserestapi.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
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
        if (id == null)
            throw new BadRequestException(clazzEntity.getSimpleName() + ": id must be not empty");
        else {
            try {
                Optional<E> optional = repo.findById(id);
                if (!optional.isPresent()) {
                    throw new BadRequestException(String.format("Does not exist %s with id = %s",
                            clazzEntity.getSimpleName(),
                            String.valueOf(id)));
                }
                return optional.get();
            } catch (NoSuchElementException e) {
                throw new BadRequestException(String.format("Does not exist %s with id = %s",
                        clazzEntity.getSimpleName(),
                        String.valueOf(id)));
            }
        }
    }

    @Override
    public List<E> findAll() {
        return repo.findAll();
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
//    @Override
//    public <REQUEST, RESPONSE> RESPONSE create(REQUEST request) {
////        try {
////            E entity = clazzEntity.newInstance();
////            MappingUtils.mapping(request, entity);
////            save(entity);
////            RESPONSE response = MappingUtils.mapping(entity, )
////        } catch (Exception ex) {
////
////        }
//        return null;
//    }
}
