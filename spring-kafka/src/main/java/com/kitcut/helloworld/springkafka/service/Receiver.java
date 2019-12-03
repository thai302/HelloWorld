package com.kitcut.helloworld.springkafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Receiver {
    private CountDownLatch latch = new CountDownLatch(1);

    @KafkaListener(
            topics = "test",
            groupId = "userConsumerFactory")
    public void receive(String data) {
        latch.countDown();
    }

//    @KafkaListener(
//            topics = "test",
//            groupId = "consumerFactory1")
//    public void receive1(User1 data) throws Exception{
////        String model = new ObjectMapper().readValue(data, String.class);
////        User model = new ObjectMapper().readValue(data, User.class);
//        latch.countDown();
//    }

//    @KafkaListener(
//            topics = "${spring.kafka.topic.request-topic}")
//    @SendTo
//    public Model receive(Model request) {
//
//        int sum = request.getFirstNumber() + request.getSecondNumber();
//        request.setAdditionalProperty("sum", sum);
//        return request;
//    }

    @KafkaListener(
            topics = "${spring.kafka.topic.request-topic}")
    @SendTo
    public String receive1(Object request) {

        return null;
    }
}
