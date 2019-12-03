package com.kitcut.helloworld.springkafka.config.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

@Configuration
public class KafkaProducerReplyConfig1 extends KafkaProducerConfigBase {

    @Value("${spring.kafka.topic.reply-topic}")
    private String replyTopic;

    @Bean
    public ReplyingKafkaTemplate<String, String, String> replyKafkaTemplate1(
            KafkaMessageListenerContainer<String, String> container) {
        ReplyingKafkaTemplate<String, String, String> replyTemplate = new ReplyingKafkaTemplate<>(producerFactory(), container);
//        replyTemplate.setReplyTimeout(30000);
//        replyTemplate.setSharedReplyTopic(true);

        return replyTemplate;
    }

    @Bean
    public KafkaMessageListenerContainer<String, String> replyContainer1(ConsumerFactory<String, String> cf) {
        ContainerProperties containerProperties = new ContainerProperties(replyTopic);
        return new KafkaMessageListenerContainer<>(cf, containerProperties);
    }
}
