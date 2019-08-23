package com.javainuse.service;

import com.javainuse.model.Employee;

public interface RabbitMQSenderInterface {
    void send(Employee company);
}
