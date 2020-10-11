package com.taximicroservice.chatservice.config.kafka;

import com.taximicroservice.chatservice.model.dto.BookingResponseDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Autowired
    private KafkaConfigProps kafkaConfigProps;

    private Map<String, Object> getBookingDataByIdConsumerConfig() {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfigProps.bootstrapAddress);
        configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        configMap.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConfigProps.groupId);
        return configMap;
    }

    @Bean
    public ConsumerFactory<String, BookingResponseDTO> getBookingDataByIdConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(getBookingDataByIdConsumerConfig(), new StringDeserializer(),
                new JsonDeserializer<>(BookingResponseDTO.class, false));
    }

}
