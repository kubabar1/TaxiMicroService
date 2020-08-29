package com.taximicroservice.passengerservice.config.kafka;

import com.taximicroservice.passengerservice.model.PassengerResponseDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Autowired
    private KafkaConfigProps kafkaConfigProps;


    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfigProps.bootstrapAddress);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        // props.put(ProducerConfig.ACKS_CONFIG, "all");
        // props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConfigProps.groupId);

        return props;
    }

    @Bean
    public ConsumerFactory<String, PassengerResponseDTO> replyConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(),
                new JsonDeserializer<>(PassengerResponseDTO.class, false));
    }

    @Bean
    public KafkaMessageListenerContainer<String, PassengerResponseDTO> replyListenerContainer() {
        ContainerProperties containerProperties = new ContainerProperties(kafkaConfigProps.addPassengerReplyTopic);
        return new KafkaMessageListenerContainer<>(replyConsumerFactory(), containerProperties);
    }

}
