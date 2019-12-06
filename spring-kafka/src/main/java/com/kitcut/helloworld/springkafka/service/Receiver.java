package com.kitcut.helloworld.springkafka.service;

import com.kitcut.helloworld.springkafka.dto.Model;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Receiver {
    private CountDownLatch latch = new CountDownLatch(1);

//    @KafkaListener(
//            topics = "test",
//            groupId = "userConsumerFactory")
//    public void receive(String data) {
//        latch.countDown();
//    }
//
//    @KafkaListener(
//            topics = "test",
//            groupId = "consumerFactory1")
//    public void receive1(User data) throws Exception{
////        String model = new ObjectMapper().readValue(data, String.class);
////        User model = new ObjectMapper().readValue(data, User.class);
//        latch.countDown();
//    }

    @KafkaListener(
            topics = "${spring.kafka.topic.request-topic}",
    groupId = "group1")
    @SendTo
    public Model receive(Model request) {

        int sum = request.getFirstNumber() + request.getSecondNumber();
        request.setAdditionalProperty("sum", sum);
        return request;
    }

//    @KafkaListener(
//            topics = "request-topic-3",
//            groupId = "group2")
//    @SendTo
//    public String receive1(String request) {
//
//        return "haha";
//    }
}
