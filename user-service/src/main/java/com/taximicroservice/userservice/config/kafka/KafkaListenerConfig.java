package com.taximicroservice.userservice.config.kafka;

import com.taximicroservice.userservice.model.dto.kafka.PageRequestDTO;
import com.taximicroservice.userservice.model.dto.UserAddDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

@Configuration
public class KafkaListenerConfig {

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, UserAddDTO>>
    requestUserAddDTOListenerContainerFactory(KafkaConsumerConfig kafkaConsumerConfig, KafkaProducerConfig kafkaProducerConfig) {
        ConcurrentKafkaListenerContainerFactory<String, UserAddDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(kafkaConsumerConfig.requestUserAddDTOConsumerFactory());
        factory.setReplyTemplate(kafkaProducerConfig.replyUserResponseDTOTemplate());
        return factory;
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, PageRequestDTO>>
    requestUserResponseDTOPageListenerContainerFactory(KafkaConsumerConfig kafkaConsumerConfig, KafkaProducerConfig kafkaProducerConfig) {
        ConcurrentKafkaListenerContainerFactory<String, PageRequestDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(kafkaConsumerConfig.requestPageRequestDTOConsumerFactory());
        factory.setReplyTemplate(kafkaProducerConfig.replyUserResponseDTOPageTemplate());
        return factory;
    }

}
