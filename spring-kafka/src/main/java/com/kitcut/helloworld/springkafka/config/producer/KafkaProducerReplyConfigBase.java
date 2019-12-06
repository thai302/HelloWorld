package com.kitcut.helloworld.springkafka.config.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;

public class KafkaProducerReplyConfigBase<K, V> extends KafkaProducerConfigBase {

    @Autowired
    private ConsumerFactory<K, V> cf;

//    @Bean
//    public ReplyingKafkaTemplate<K, V, R> replyKafkaTemplate1(
//            KafkaMessageListenerContainer<K, R> container) {
//        ReplyingKafkaTemplate<K, V, R> replyTemplate = new ReplyingKafkaTemplate<>(producerFactory1(), container);
////        replyTemplate.setReplyTimeout(30000);
////        replyTemplate.setSharedReplyTopic(true);
//
//        return replyTemplate;
//    }

//    @Bean
    protected KafkaMessageListenerContainer<K, V> replyContainer(String replyTopic) {
        ContainerProperties containerProperties = new ContainerProperties(replyTopic);
//        Pattern pattern = Pattern.compile(".*");
//        ContainerProperties containerProperties = new ContainerProperties(pattern);
        return new KafkaMessageListenerContainer<>(cf, containerProperties);
    }
}
