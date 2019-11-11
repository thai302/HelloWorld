package com.kitcut.helloworld.springsecurity.repository;

import com.kitcut.helloworld.springsecurity.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);
}
