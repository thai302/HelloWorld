package com.kitcut.helloworld.axonsagapattern.orderservice.aggregate.service.impl;

import com.kitcut.helloworld.axonsagapattern.coreapis.command.CreateOrderCommand;
import com.kitcut.helloworld.axonsagapattern.orderservice.aggregate.dto.OrderCreateDTO;
import com.kitcut.helloworld.axonsagapattern.orderservice.aggregate.enums.OrderStatus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;
import com.kitcut.helloworld.axonsagapattern.orderservice.aggregate.service.OrderCommandService;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class OrderCommandServiceImpl implements OrderCommandService {

    private final CommandGateway commandGateway;

    public OrderCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<String> createOrder(OrderCreateDTO orderCreateDTO) {
        return commandGateway.send(new CreateOrderCommand(UUID.randomUUID().toString(), orderCreateDTO.getItemType(),
                orderCreateDTO.getPrice(), orderCreateDTO.getCurrency(), String.valueOf(OrderStatus.CREATED)));
    }
}
