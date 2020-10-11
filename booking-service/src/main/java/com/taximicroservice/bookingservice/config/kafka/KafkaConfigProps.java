package com.taximicroservice.bookingservice.config.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfigProps {

    @Value(value = "${bookingService.kafka.bootstrapAddress}")
    public String bootstrapAddress;

    @Value(value = "${bookingService.kafka.groupId}")
    public String groupId;

    @Value(value = "${bookingService.kafka.topics.getUserById}")
    public String getUserByIdTopic;

    @Value(value = "${bookingService.kafka.topics.getUserByIdReply}")
    public String getUserByIdReplyTopic;

}
