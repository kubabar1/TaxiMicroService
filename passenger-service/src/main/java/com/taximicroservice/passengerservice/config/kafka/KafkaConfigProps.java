package com.taximicroservice.passengerservice.config.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfigProps {

    @Value(value = "${passengerService.kafka.bootstrapAddress}")
    protected String bootstrapAddress;

    @Value(value = "${passengerService.kafka.groupId}")
    protected String groupId;

    @Value(value = "${passengerService.kafka.topics.addPassengerReplyTopic}")
    protected String addPassengerReplyTopic;

}
