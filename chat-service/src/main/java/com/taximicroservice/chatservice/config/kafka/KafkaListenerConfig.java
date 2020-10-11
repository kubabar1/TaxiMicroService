package com.taximicroservice.chatservice.config.kafka;

import com.taximicroservice.chatservice.model.dto.BookingResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

@Configuration
public class KafkaListenerConfig {

    @Autowired
    private KafkaConfigProps kafkaConfigProps;

    @Bean
    public KafkaMessageListenerContainer<String, BookingResponseDTO> getBookingDataByIdListenerContainer(
            ConsumerFactory<String, BookingResponseDTO> getBookingDataByIdConsumerFactory) {
        ContainerProperties containerProperties = new ContainerProperties(kafkaConfigProps.getBookingBookingDataByIdReplyTopic);
        return new KafkaMessageListenerContainer<>(getBookingDataByIdConsumerFactory, containerProperties);
    }

    @Bean
    public ReplyingKafkaTemplate<String, Long, BookingResponseDTO> getBookingDataByIdReplyingKafkaTemplate(
            ProducerFactory<String, Long> getBookingDataByIdProducerFactory,
            KafkaMessageListenerContainer<String, BookingResponseDTO> getBookingDataByIdListenerContainer) {
        return new ReplyingKafkaTemplate<>(getBookingDataByIdProducerFactory, getBookingDataByIdListenerContainer);
    }

}
