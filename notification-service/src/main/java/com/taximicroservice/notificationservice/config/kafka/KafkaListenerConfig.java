package com.taximicroservice.notificationservice.config.kafka;

import com.taximicroservice.notificationservice.model.dto.NotificationRequestDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

@Configuration
public class KafkaListenerConfig {

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, NotificationRequestDTO>>
    notificationDTOListenerContainerFactory(KafkaConsumerConfig kafkaConsumerConfig) {
        ConcurrentKafkaListenerContainerFactory<String, NotificationRequestDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(kafkaConsumerConfig.notificationDTOConsumerFactory());
        return factory;
    }

}
