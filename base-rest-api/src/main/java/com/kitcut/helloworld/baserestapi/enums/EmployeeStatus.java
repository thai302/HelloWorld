package com.kitcut.helloworld.baserestapi.enums;

public enum EmployeeStatus implements BaseEnum<Integer> {
    ACTIVE(3),
    BLOCK(4)
    ;

    private Integer value;

    EmployeeStatus(Integer value){
        this.value = value;
    }

    @Override
    public Integer getValue(){
        return value;
    }
}
