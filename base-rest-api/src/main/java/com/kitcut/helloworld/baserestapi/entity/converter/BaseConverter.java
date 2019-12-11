package com.kitcut.helloworld.baserestapi.entity.converter;


import com.kitcut.helloworld.baserestapi.enums.BaseEnum;

import javax.persistence.AttributeConverter;

public abstract class BaseConverter<X extends BaseEnum<Y>, Y> implements AttributeConverter<X, Y> {

    @Override
    public Y convertToDatabaseColumn(X category) {
        if (category == null) {
            return null;
        }
        return category.getValue();
    }
}