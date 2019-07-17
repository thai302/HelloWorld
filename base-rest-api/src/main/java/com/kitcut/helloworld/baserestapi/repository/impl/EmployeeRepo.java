package com.kitcut.helloworld.baserestapi.repository.impl;

import com.kitcut.helloworld.baserestapi.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeEntity, Long> {
}
