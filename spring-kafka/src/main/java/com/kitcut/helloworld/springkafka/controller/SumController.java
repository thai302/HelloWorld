package com.kitcut.helloworld.springkafka.controller;

import com.kitcut.helloworld.springkafka.dto.Model;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class SumController {

    @Autowired
    ReplyingKafkaTemplate<String, Model, Model> kafkaTemplate;

    @Value("${spring.kafka.topic.request-topic}")
    String requestTopic;

    @Value("${spring.kafka.topic.reply-topic}")
    String requestReplyTopic;

    @ResponseBody
    @PostMapping(value = "/sum", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Model sum(@RequestBody Model request) throws InterruptedException, ExecutionException {
        // create producer record
        ProducerRecord<String, Model> record = new ProducerRecord<String, Model>(requestTopic, request);
        // set reply topic in header
        record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, requestReplyTopic.getBytes()));
        // post in kafka topic
        RequestReplyFuture<String, Model, Model> sendAndReceive = kafkaTemplate.sendAndReceive(record);

        // confirm if producer produced successfully
        SendResult<String, Model> sendResult = sendAndReceive.getSendFuture().get();

        //print all headers
        sendResult.getProducerRecord().headers().forEach(header -> System.out.println(header.key() + ":" + header.value().toString()));

        // get consumer record
        ConsumerRecord<String, Model> consumerRecord = sendAndReceive.get();
        // return consumer value
        return consumerRecord.value();
    }

}
