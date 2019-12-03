//package com.kitcut.helloworld.springkafka.config.consumer;
//
//import com.kitcut.helloworld.springkafka.dto.User1;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//
//@EnableKafka
//@Configuration
//public class KafkaConsumerUser1Config extends KafkaConsumerConfigBase<User1> {
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, User1> userConsumerFactory1() {
//        ConsumerFactory<String, User1> consumerFactory = consumerFactory("userConsumerFactory1");
//        return getKafkaListenerContainerFactory(consumerFactory);
//    }
//}