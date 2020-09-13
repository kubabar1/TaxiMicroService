package com.taximicroservice.bookingservice.config.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfigProps {

    @Value(value = "${bookingService.kafka.bootstrapAddress}")
    protected String bootstrapAddress;

    @Value(value = "${bookingService.kafka.groupId}")
    protected String groupId;

    @Value(value = "${bookingService.kafka.topics.getUserById}")
    protected String getUserByIdTopic;

    @Value(value = "${bookingService.kafka.topics.getUserByIdReply}")
    protected String getUserByIdReplyTopic;

}
