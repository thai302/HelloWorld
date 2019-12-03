//package com.kitcut.helloworld.springkafka.config.consumer;
//
//import com.kitcut.helloworld.springkafka.dto.User;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//
//@EnableKafka
//@Configuration
//public class KafkaConsumerUserConfig extends KafkaConsumerConfigBase<User> {
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, User> userConsumerFactory() {
//        ConsumerFactory<String, User> consumerFactory = consumerFactory("userConsumerFactory");
//        return getKafkaListenerContainerFactory(consumerFactory);
//    }
//}