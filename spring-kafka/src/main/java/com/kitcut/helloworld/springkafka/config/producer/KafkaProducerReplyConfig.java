package com.kitcut.helloworld.springkafka.config.producer;

import com.kitcut.helloworld.springkafka.dto.Model;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

@Configuration
public class KafkaProducerReplyConfig extends KafkaProducerConfigBase {

    @Value("${spring.kafka.topic.reply-topic}")
    private String replyTopic;

    @Bean
    public ReplyingKafkaTemplate<String, Model, Model> replyKafkaTemplate(
            KafkaMessageListenerContainer<String, Model> container) {
        ReplyingKafkaTemplate<String, Model, Model> replyTemplate = new ReplyingKafkaTemplate<>(producerFactory(), container);
//        replyTemplate.setReplyTimeout(30000);
//        replyTemplate.setSharedReplyTopic(true);

        return replyTemplate;
    }

    @Bean
    public KafkaMessageListenerContainer<String, Model> replyContainer(ConsumerFactory<String, Model> cf) {
        ContainerProperties containerProperties = new ContainerProperties(replyTopic);
        return new KafkaMessageListenerContainer<>(cf, containerProperties);
    }
}
