package com.taximicroservice.passengerservice.config;

import com.taximicroservice.passengerservice.model.PassengerAddDTO;
import com.taximicroservice.passengerservice.model.PassengerResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

@Configuration
public class KafkaListenerConfig {

    @Autowired
    private KafkaConfigProps kafkaConfigProps;

    @Bean
    public ReplyingKafkaTemplate<String, PassengerAddDTO, PassengerResponseDTO> replyKafkaTemplate(ProducerFactory<String, PassengerAddDTO> pf,
                                                                                                   KafkaMessageListenerContainer<String, PassengerResponseDTO> lc) {
        return new ReplyingKafkaTemplate<>(pf, lc);
    }

}
