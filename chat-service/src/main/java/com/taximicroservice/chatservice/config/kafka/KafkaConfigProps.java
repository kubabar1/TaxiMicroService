package com.taximicroservice.chatservice.config.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfigProps {

    @Value(value = "${chatService.kafka.bootstrapAddress}")
    public String bootstrapAddress;

    @Value(value = "${chatService.kafka.chatGroupId}")
    public String groupId;

    @Value(value = "${chatService.kafka.topics.getBookingBookingDataByIdTopic}")
    public String getBookingBookingDataByIdTopic;

    @Value(value = "${chatService.kafka.topics.getBookingBookingDataByIdReplyTopic}")
    public String getBookingBookingDataByIdReplyTopic;

}
