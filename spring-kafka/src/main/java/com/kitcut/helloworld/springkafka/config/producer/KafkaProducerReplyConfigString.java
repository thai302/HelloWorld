package com.kitcut.helloworld.springkafka.config.producer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

@Configuration
public class KafkaProducerReplyConfigString extends KafkaProducerReplyConfigBase<String, String> {

    @Bean
    public ReplyingKafkaTemplate<String, String, String> replyKafkaTemplate2() {
        String replyTopic = "reply-topic-3";
        ProducerFactory<String, String> producerFactory = producerFactory();
        KafkaMessageListenerContainer<String, String> replyContainer = replyContainer(replyTopic);

        ReplyingKafkaTemplate<String, String, String> replyTemplate = new ReplyingKafkaTemplate<>(producerFactory, replyContainer);
//        replyTemplate.setReplyTimeout(30000);
//        replyTemplate.setSharedReplyTopic(true);

        return replyTemplate;
    }
}
