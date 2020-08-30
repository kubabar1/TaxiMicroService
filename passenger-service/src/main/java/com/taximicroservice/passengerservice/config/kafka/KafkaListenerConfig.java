package com.taximicroservice.passengerservice.config.kafka;

import com.taximicroservice.passengerservice.model.PageRequestDTO;
import com.taximicroservice.passengerservice.model.PassengerAddDTO;
import com.taximicroservice.passengerservice.model.PassengerResponseDTO;
import com.taximicroservice.passengerservice.model.PassengerResponseDTOPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

@Configuration
public class KafkaListenerConfig {

    @Bean
    public ReplyingKafkaTemplate<String, PassengerAddDTO, PassengerResponseDTO>
    replyAddPassengerKafkaTemplate(ProducerFactory<String, PassengerAddDTO> pf,
                                   KafkaMessageListenerContainer<String, PassengerResponseDTO> lc) {
        return new ReplyingKafkaTemplate<>(pf, lc);
    }

    @Bean
    public ReplyingKafkaTemplate<String, PageRequestDTO, PassengerResponseDTOPage>
    replyGetPassengersPageKafkaTemplate(ProducerFactory<String, PageRequestDTO> pf,
                                        KafkaMessageListenerContainer<String, PassengerResponseDTOPage> lc) {
        return new ReplyingKafkaTemplate<>(pf, lc);
    }

}
