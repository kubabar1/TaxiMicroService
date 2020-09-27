package com.taximicroservice.notificationservice.config.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfigProps {

    @Value(value = "${notificationService.kafka.bootstrapAddress}")
    protected String bootstrapAddress;

    @Value(value = "${notificationService.kafka.groupId}")
    protected String groupId;

    @Value(value = "${notificationService.kafka.topics.sendUserNotificationTopic}")
    protected String sendUserNotificationTopic;

}
