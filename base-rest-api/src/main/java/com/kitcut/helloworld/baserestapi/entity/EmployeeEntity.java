package com.kitcut.helloworld.baserestapi.entity;

import com.kitcut.helloworld.baserestapi.entity.converter.EmployeeStatusConverter;
import com.kitcut.helloworld.baserestapi.enums.EmployeeStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@Entity(name = "employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

//    @Column(name = "name")
//    private String name;

    @Column(name = "birthday")
    private Date birthday;

//    @Column(name = "address")
//    private String address;

    @Column(name = "status")
    @Convert(converter = EmployeeStatusConverter.class)
    private EmployeeStatus status;

}


