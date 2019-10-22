package com.kitcut.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeRs {
    private String firstName;
    private int id;

//    public EmployeeRs(String firstName){
//        this.firstName = firstName;
//    }

    public EmployeeRs(){}
}
