package com.kitcut.helloworld.axonsagapattern.orderservice.service;

import com.kitcut.helloworld.axonsagapattern.orderservice.dto.OrderCreateDTO;

import java.util.concurrent.CompletableFuture;

public interface OrderCommandService {

    CompletableFuture<String> createOrder(OrderCreateDTO orderCreateDTO);

}
