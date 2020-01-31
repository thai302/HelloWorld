package com.kitcut.helloworld.axonsagapattern.orderservice.aggregate.service;

import com.kitcut.helloworld.axonsagapattern.orderservice.aggregate.dto.OrderCreateDTO;

import java.util.concurrent.CompletableFuture;

public interface OrderCommandService {

    CompletableFuture<String> createOrder(OrderCreateDTO orderCreateDTO);

}
