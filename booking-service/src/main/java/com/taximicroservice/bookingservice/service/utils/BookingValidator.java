package com.taximicroservice.bookingservice.service.utils;

import com.taximicroservice.bookingservice.exception.BookingServiceException;
import com.taximicroservice.bookingservice.model.dto.kafka.UserResponseDTO;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class BookingValidator {

    @Value(value = "${bookingService.kafka.topics.getUserById}")
    protected String getUserByIdTopic;

    @Value(value = "${bookingService.kafka.topics.getUserByIdReply}")
    private String getUserByIdReplyTopic;

    @Autowired
    private ReplyingKafkaTemplate<String, Long, UserResponseDTO> replyUserResponseDTOListenerContainer;


    public void validatePageAndCount(int page, int count) throws BookingServiceException {
        if (page < 0) {
            throw new BookingServiceException("Page must be greater than or equal 0");
        }
        if (count <= 0) {
            throw new BookingServiceException("Count must be greater than 0");
        }
    }

    public void validateUserId(Long userId) throws BookingServiceException {
        ProducerRecord<String, Long> record = new ProducerRecord<>(getUserByIdTopic, userId);
        record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, getUserByIdReplyTopic.getBytes()));

        try {
            UserResponseDTO userResponseDTO = replyUserResponseDTOListenerContainer.sendAndReceive(record).get().value();
            long userIdResponse = userResponseDTO.getId();
            if (userId != userIdResponse) {
                throw new BookingServiceException("User ID validation failed");
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new BookingServiceException("User ID validation failed");
        }
    }

    public void validateBookingCurrentStatus(List<String> expectedBookingStatusIdList, String currentBookingStatusId) throws BookingServiceException {
        if (!expectedBookingStatusIdList.contains(currentBookingStatusId)) {
            throw new BookingServiceException("Invalid booking status");
        }
    }

}
