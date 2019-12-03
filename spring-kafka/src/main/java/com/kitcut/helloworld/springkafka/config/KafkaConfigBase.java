package com.kitcut.helloworld.springkafka.config;

import org.springframework.beans.factory.annotation.Value;

public class KafkaConfigBase {

    @Value("${spring.kafka.bootstrap-servers}")
    protected String bootstrapServers;
}