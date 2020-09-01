package com.taximicroservice.driverservice.config.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfigProps {

    @Value(value = "${driverService.kafka.bootstrapAddress}")
    protected String bootstrapAddress;

    @Value(value = "${driverService.kafka.groupId}")
    protected String groupId;

    @Value(value = "${driverService.kafka.topics.addDriverReplyTopic}")
    protected String addDriverReplyTopic;

    @Value(value = "${driverService.kafka.topics.getDriversPageReplyTopic}")
    protected String getDriversPageReplyTopic;

}
