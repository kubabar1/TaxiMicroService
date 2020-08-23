package com.taximicroservice.passengerservice.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicsConfig {

    @Value(value = "${passengerService.kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Value(value = "${passengerService.kafka.addPassengerTopic}")
    private String addPassengerTopic;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic addPassengerTopic() {
        return new NewTopic(addPassengerTopic, 1, (short) 1);
    }

}
