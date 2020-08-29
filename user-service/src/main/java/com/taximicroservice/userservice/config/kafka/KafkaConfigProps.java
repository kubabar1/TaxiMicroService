package com.taximicroservice.userservice.config.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfigProps {

    @Value(value = "${userService.kafka.bootstrapAddress}")
    protected String bootstrapAddress;

    @Value(value = "${userService.kafka.groupId}")
    protected String groupId;

    @Value(value = "${userService.kafka.topics.addPassengerReplyTopic}")
    protected String addPassengerReplyTopic;

}
