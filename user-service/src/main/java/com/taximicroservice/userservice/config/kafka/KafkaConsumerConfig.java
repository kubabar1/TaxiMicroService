package com.taximicroservice.userservice.config.kafka;

import com.taximicroservice.userservice.model.dto.kafka.PageRequestDTO;
import com.taximicroservice.userservice.model.dto.UserAddDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig  {

    @Autowired
    private KafkaConfigProps kafkaConfigProps;


    @Bean
    public Map<String, Object> consumerConfigs1() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfigProps.bootstrapAddress);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConfigProps.passengerGroupId);
        return props;
    }

    @Bean
    public Map<String, Object> consumerConfigs2() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfigProps.bootstrapAddress);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConfigProps.driverGroupId);
        return props;
    }

    @Bean
    public Map<String, Object> consumerConfigs3() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfigProps.bootstrapAddress);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConfigProps.bookingGroupId);
        return props;
    }

    @Bean
    public ConsumerFactory<String, UserAddDTO> requestUserAddDTOConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs1(), new StringDeserializer(),
                new JsonDeserializer<>(UserAddDTO.class, false));
    }

    @Bean
    public ConsumerFactory<String, PageRequestDTO> requestPageRequestDTOConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs1(), new StringDeserializer(),
                new JsonDeserializer<>(PageRequestDTO.class, false));
    }

    @Bean
    public ConsumerFactory<String, Long> requestUserResponseByIdConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs3(), new StringDeserializer(),
                new LongDeserializer());
    }

}
