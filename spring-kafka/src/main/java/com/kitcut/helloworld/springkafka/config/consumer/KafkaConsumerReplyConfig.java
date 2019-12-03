package com.kitcut.helloworld.springkafka.config.consumer;

import com.kitcut.helloworld.springkafka.dto.Model;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
public class KafkaConsumerReplyConfig extends KafkaConsumerConfigBase<Model> {

    @Autowired
    private ReplyingKafkaTemplate<String, Model, Model> replyingKafkaTemplate;

    @Bean
    public ConsumerFactory<String, Model> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                consumerConfigs("modelConsumerFactory"),
                new StringDeserializer(),
                new JsonDeserializer<>(Model.class));
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Model>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Model> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setReplyTemplate(replyingKafkaTemplate);
        return factory;
    }
}