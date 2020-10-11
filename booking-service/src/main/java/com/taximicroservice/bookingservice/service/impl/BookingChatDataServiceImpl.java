package com.taximicroservice.bookingservice.service.impl;

import com.taximicroservice.bookingservice.config.kafka.KafkaConfigProps;
import com.taximicroservice.bookingservice.exception.ExternalServiceException;
import com.taximicroservice.bookingservice.model.dto.kafka.BookingChatResponseDTO;
import com.taximicroservice.bookingservice.model.dto.kafka.UserResponseDTO;
import com.taximicroservice.bookingservice.model.entity.BookingEntity;
import com.taximicroservice.bookingservice.model.utils.BookingStatusEnum;
import com.taximicroservice.bookingservice.repository.BookingRepository;
import com.taximicroservice.bookingservice.repository.BookingStatusRepository;
import com.taximicroservice.bookingservice.service.BookingChatDataService;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Service
public class BookingChatDataServiceImpl implements BookingChatDataService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private KafkaConfigProps kafkaConfigProps;

    @Autowired
    private ReplyingKafkaTemplate<String, Long, UserResponseDTO> replyUserResponseDTOListenerContainer;

    @KafkaListener(groupId = "${bookingService.kafka.groups.chatServiceGroupId}",
            topics = "${bookingService.kafka.topics.getBookingBookingDataByIdTopic}",
            containerFactory = "getBookingDataByIdListenerContainerFactory")
    @SendTo()
    @Override
    public BookingChatResponseDTO getBookingChatDataById(Long bookingId) {
        BookingEntity bookingEntity = bookingRepository.findById(bookingId).orElse(null);

        BookingChatResponseDTO bookingChatResponseDTO = new BookingChatResponseDTO();

        if (Objects.isNull(bookingEntity)) {
            return bookingChatResponseDTO;
        }

        bookingChatResponseDTO.setBookingStatus(BookingStatusEnum.valueOf(bookingEntity.getStatus().getId()));
        bookingChatResponseDTO.setDriverUsername(getUsernameByUserId(bookingEntity.getDriverId()));
        bookingChatResponseDTO.setPassengerUsername(getUsernameByUserId(bookingEntity.getPassengerId()));

        return bookingChatResponseDTO;
    }

    private String getUsernameByUserId(Long userId) {
        ProducerRecord<String, Long> record = new ProducerRecord<>(kafkaConfigProps.getUserByIdTopic, userId);
        record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, kafkaConfigProps.getUserByIdReplyTopic.getBytes()));
        try {
            return replyUserResponseDTOListenerContainer.sendAndReceive(record).get().value().getUserName();
        } catch (InterruptedException | ExecutionException e) {
            throw new ExternalServiceException("Cannot receive username from external service");
        }
    }

}
