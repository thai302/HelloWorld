package com.kitcut.helloworld.springkafka.config.producer;

import com.kitcut.helloworld.springkafka.dto.Model;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

@Configuration
@Getter
public class KafkaProducerReplyConfigModel extends KafkaProducerReplyConfigBase<String, Model> {

//    private String replyTopic = "reply-topic-3";

    @Bean
    public ReplyingKafkaTemplate<String, Model, Model> replyKafkaTemplate() {
        String replyTopic = "reply-topic";
        ProducerFactory<String, Model> producerFactory = producerFactory();
        KafkaMessageListenerContainer<String, Model> replyContainer = replyContainer(replyTopic);

        ReplyingKafkaTemplate<String, Model, Model> replyTemplate = new ReplyingKafkaTemplate<>(producerFactory, replyContainer);
//        replyTemplate.setReplyTimeout(30000);
//        replyTemplate.setSharedReplyTopic(true);
        return replyTemplate;
    }
}
