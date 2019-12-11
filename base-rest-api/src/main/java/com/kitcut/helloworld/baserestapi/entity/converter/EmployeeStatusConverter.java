package com.kitcut.helloworld.baserestapi.entity.converter;


import com.kitcut.helloworld.baserestapi.enums.EmployeeStatus;

import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter
public class EmployeeStatusConverter extends BaseConverter<EmployeeStatus, Integer>{

    @Override
    public EmployeeStatus convertToEntityAttribute(Integer value) {
        if (value == null) {
            return null;
        }

        return Stream.of(EmployeeStatus.values())
                .filter(c -> c.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}