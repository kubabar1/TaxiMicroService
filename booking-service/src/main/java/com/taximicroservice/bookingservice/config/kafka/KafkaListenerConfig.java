package com.taximicroservice.bookingservice.config.kafka;

import com.taximicroservice.bookingservice.model.dto.kafka.BookingChatResponseDTO;
import com.taximicroservice.bookingservice.model.dto.kafka.UserResponseDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

@Configuration
public class KafkaListenerConfig {

    @Bean
    public KafkaMessageListenerContainer<String, UserResponseDTO> replyDriverListenerContainer(
            ConsumerFactory<String, UserResponseDTO> replyUserResponseDTOConsumerFactory,
            KafkaConfigProps kafkaConfigProps) {
        ContainerProperties containerProperties = new ContainerProperties(kafkaConfigProps.getUserByIdReplyTopic);
        return new KafkaMessageListenerContainer<>(replyUserResponseDTOConsumerFactory, containerProperties);
    }

    @Bean
    public ReplyingKafkaTemplate<String, Long, UserResponseDTO>
    replyGetUserByIdKafkaTemplate(ProducerFactory<String, Long> pf,
                                  KafkaMessageListenerContainer<String, UserResponseDTO> lc) {
        return new ReplyingKafkaTemplate<>(pf, lc);
    }


    @Bean
    public KafkaTemplate<String, BookingChatResponseDTO> replyGetBookingDataByIdKafkaTemplate(
            ProducerFactory<String, BookingChatResponseDTO> getBookingDataByIdProducerFactory) {
        return new KafkaTemplate<>(getBookingDataByIdProducerFactory);
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Long>> getBookingDataByIdListenerContainerFactory(
            ConsumerFactory<String, Long> getBookingDataByIdConsumerFactory,
            KafkaTemplate<String, BookingChatResponseDTO> replyGetBookingDataByIdKafkaTemplate) {
        ConcurrentKafkaListenerContainerFactory<String, Long> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(getBookingDataByIdConsumerFactory);
        factory.setReplyTemplate(replyGetBookingDataByIdKafkaTemplate);
        return factory;
    }

}
