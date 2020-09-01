package com.taximicroservice.driverservice.config.kafka;

import com.taximicroservice.driverservice.model.DriverAddDTO;
import com.taximicroservice.driverservice.model.DriverResponseDTO;
import com.taximicroservice.driverservice.model.DriverResponseDTOPage;
import com.taximicroservice.driverservice.model.PageRequestDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

@Configuration
public class KafkaListenerConfig {

    @Bean
    public ReplyingKafkaTemplate<String, DriverAddDTO, DriverResponseDTO>
    replyAddDriverKafkaTemplate(ProducerFactory<String, DriverAddDTO> pf,
                                KafkaMessageListenerContainer<String, DriverResponseDTO> lc) {
        return new ReplyingKafkaTemplate<>(pf, lc);
    }

    @Bean
    public ReplyingKafkaTemplate<String, PageRequestDTO, DriverResponseDTOPage>
    replyGetDriversPageKafkaTemplate(ProducerFactory<String, PageRequestDTO> pf,
                                     KafkaMessageListenerContainer<String, DriverResponseDTOPage> lc) {
        return new ReplyingKafkaTemplate<>(pf, lc);
    }

}
