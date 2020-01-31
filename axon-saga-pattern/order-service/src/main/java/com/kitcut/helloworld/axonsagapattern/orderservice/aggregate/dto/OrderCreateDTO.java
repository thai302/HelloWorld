package com.kitcut.helloworld.axonsagapattern.orderservice.aggregate.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderCreateDTO {
    private String itemType;

    private BigDecimal price;

    private String currency;

    private String orderStatus;
}
