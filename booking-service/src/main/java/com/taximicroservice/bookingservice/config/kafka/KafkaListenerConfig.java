package com.taximicroservice.bookingservice.config.kafka;

import com.taximicroservice.bookingservice.model.dto.kafka.UserResponseDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

@Configuration
public class KafkaListenerConfig {

    @Bean
    public ReplyingKafkaTemplate<String, Long, UserResponseDTO>
    replyGetUserByIdKafkaTemplate(ProducerFactory<String, Long> pf,
                                  KafkaMessageListenerContainer<String, UserResponseDTO> lc) {
        return new ReplyingKafkaTemplate<>(pf, lc);
    }

}
