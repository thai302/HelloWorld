package com.kitcut.helloworld.springkafka.config.consumer;

import com.kitcut.helloworld.springkafka.config.KafkaConfigBase;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

public class KafkaConsumerConfigBase<V> extends KafkaConfigBase {

    protected Map<String, Object> consumerConfigs(String groupIdConfig) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupIdConfig);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest ");

        return props;
    }

    protected ConsumerFactory<String, V> consumerFactory(String groupIdConfig) {
        final JsonDeserializer<V> jsonDeserializer = new JsonDeserializer<>();
        jsonDeserializer.addTrustedPackages("*");

        return new DefaultKafkaConsumerFactory<>(
                consumerConfigs(groupIdConfig), new StringDeserializer(), jsonDeserializer
        );
    }

    protected ConcurrentKafkaListenerContainerFactory<String, V> getKafkaListenerContainerFactory(ConsumerFactory<String, V> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, V> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
//        factory.setMessageConverter(new StringJsonMessageConverter());

        return factory;
    }
}