package com.kitcut.helloworld.springkafka.config.producer;

import com.kitcut.helloworld.springkafka.config.KafkaConfigBase;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

public class KafkaProducerConfigBase extends KafkaConfigBase {

    private Map<String, Object> producerConfigs() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        //use JsonSerializer if object that is mapping belong to library that can referenced
        //else use StringSerializer

        //use JsonSerializer for ReplyKafkaTemplate
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return configProps;
    }

    protected <V> ProducerFactory<String, V> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }
}
