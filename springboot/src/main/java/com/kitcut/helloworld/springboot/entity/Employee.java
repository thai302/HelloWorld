package com.kitcut.helloworld.springboot.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
public class Employee {
    @Column(name = "id")
    @Id
    private Long id;

    @Column(name = "first_name")
    private String firstName;
}
