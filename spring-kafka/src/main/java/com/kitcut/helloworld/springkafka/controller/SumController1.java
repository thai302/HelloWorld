package com.kitcut.helloworld.springkafka.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kitcut.helloworld.springkafka.dto.Model;
import com.kitcut.helloworld.springkafka.dto.User;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SumController1 {

    @Autowired
    ReplyingKafkaTemplate<String, String, String> kafkaTemplate;

//    @Value("${spring.kafka.topic.request-topic}")
//    String requestTopic;
//
//    @Value("${spring.kafka.topic.reply-topic}")
//    String requestReplyTopic;

    @ResponseBody
    @PostMapping(value = "/sum1", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String sum(@RequestBody Model request) throws Exception {
        String requestTopic = "request-topic-3";
        String replyTopic = "reply-topic-3";

        User user = new User();
        user.setId(1L);
        user.setUsername("thai");
        ObjectMapper objectMapper = new ObjectMapper();
        String str = objectMapper.writeValueAsString(user);

        // create producer record
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(requestTopic, str);
        // set reply topic in header
        record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, replyTopic.getBytes()));
        // post in kafka topic
        RequestReplyFuture<String, String, String> sendAndReceive = kafkaTemplate.sendAndReceive(record);

        // confirm if producer produced successfully
        SendResult<String, String> sendResult = sendAndReceive.getSendFuture().get();

        //print all headers
        sendResult.getProducerRecord().headers().forEach(header -> System.out.println(header.key() + ":" + header.value().toString()));

        // get consumer record
        ConsumerRecord<String, String> consumerRecord = sendAndReceive.get();
        // return consumer value
        return consumerRecord.value();
//        return null;
    }

}
