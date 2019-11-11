package com.kitcut.helloworld.springsecurity.repository;

import com.kitcut.helloworld.springsecurity.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}
