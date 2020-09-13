package com.taximicroservice.userservice.config.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfigProps {

    @Value(value = "${userService.kafka.bootstrapAddress}")
    protected String bootstrapAddress;

    @Value(value = "${userService.kafka.passengerGroupId}")
    protected String passengerGroupId;

    @Value(value = "${userService.kafka.driverGroupId}")
    protected String driverGroupId;

    @Value(value = "${userService.kafka.bookingGroupId}")
    protected String bookingGroupId;

}
