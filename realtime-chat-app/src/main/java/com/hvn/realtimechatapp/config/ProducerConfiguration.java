package com.hvn.realtimechatapp.config;

import com.hvn.realtimechatapp.constants.KafkaConstants;
import com.hvn.realtimechatapp.model.Message;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class ProducerConfiguration {
    @Bean
    public ProducerFactory<String, Message> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigurations());
    }

    @Bean
    public Map<String, Object> producerConfigurations() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.KAFKA_BROKER);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return config;
    }
    @Bean
    public KafkaTemplate<String, Message> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}
