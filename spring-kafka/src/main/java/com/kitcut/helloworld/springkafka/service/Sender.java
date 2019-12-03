package com.kitcut.helloworld.springkafka.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {
//    @Autowired
//    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

//    public void send(String topic, Object message) {
//        kafkaTemplate.send(topic, message);
//
//        ListenableFuture<SendResult<String, Object>> future =
//                kafkaTemplate.send(topic, message);
//
//        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
//
//            @Override
//            public void onSuccess(SendResult<String, Object> result) {
//                System.out.println("Sent message=[" + message +
//                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
//            }
//            @Override
//            public void onFailure(Throwable ex) {
//                System.out.println("Unable to send message=["
//                        + message + "] due to : " + ex.getMessage());
//            }
//        });
//    }

    public void send(String topic, Object data) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String str = objectMapper.writeValueAsString(data);

            kafkaTemplate.send(topic, str);
//            kafkaTemplate.send(topic, data);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
