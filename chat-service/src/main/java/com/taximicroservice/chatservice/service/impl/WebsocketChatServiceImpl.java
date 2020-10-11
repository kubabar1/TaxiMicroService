package com.taximicroservice.chatservice.service.impl;

import com.taximicroservice.chatservice.config.kafka.KafkaConfigProps;
import com.taximicroservice.chatservice.exception.ChatServiceException;
import com.taximicroservice.chatservice.model.dto.BookingResponseDTO;
import com.taximicroservice.chatservice.model.dto.MessageDTO;
import com.taximicroservice.chatservice.model.dto.MessageRequestDTO;
import com.taximicroservice.chatservice.model.entity.MessageEntity;
import com.taximicroservice.chatservice.model.utils.BookingStatusEnum;
import com.taximicroservice.chatservice.model.utils.MessageStatusEnum;
import com.taximicroservice.chatservice.repository.MessageRepository;
import com.taximicroservice.chatservice.service.WebsocketChatService;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Service
public class WebsocketChatServiceImpl implements WebsocketChatService {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private KafkaConfigProps kafkaConfigProps;

    @Autowired
    ReplyingKafkaTemplate<String, Long, BookingResponseDTO> bookingResponseDTOReplyingKafkaTemplate;


    @Override
    public void sendMessageToUsers(Long bookingId, MessageRequestDTO messageRequestDTO) throws ChatServiceException {
        MessageDTO messageDTO = mapMessageRequestDTOToMessageDTO(bookingId, messageRequestDTO);
        try {
            BookingResponseDTO bookingResponseDTO = getBookingDataById(bookingId);

            validateBookingResponseDTO(bookingResponseDTO, bookingId);

            simpMessagingTemplate.convertAndSendToUser(bookingResponseDTO.getPassengerUsername(), "/queue/reply/bookings", messageDTO);
            simpMessagingTemplate.convertAndSendToUser(bookingResponseDTO.getDriverUsername(), "/queue/reply/bookings", messageDTO);
            saveMessageInDatabase(messageDTO);
        } catch (ExecutionException | InterruptedException e) {
            throw new ChatServiceException(e.getMessage());
        }
    }

    private void validateBookingResponseDTO(BookingResponseDTO bookingResponseDTO, Long bookingId) throws ChatServiceException {
        if (Objects.isNull(bookingResponseDTO.getDriverUsername())
                || Objects.isNull(bookingResponseDTO.getPassengerUsername())) {
            throw new ChatServiceException("Booking with ID \"" + bookingId + "\" does not exist.");
        }

        if (Objects.isNull(bookingResponseDTO.getBookingStatus())
                || (!BookingStatusEnum.ASSIGNED.equals(bookingResponseDTO.getBookingStatus())
                && !BookingStatusEnum.IN_PROGRESS.equals(bookingResponseDTO.getBookingStatus()))) {
            throw new ChatServiceException("Cannot send message to booking with status: \""
                    + bookingResponseDTO.getBookingStatus() + "\"");
        }
    }

    private MessageDTO mapMessageRequestDTOToMessageDTO(Long bookingId, MessageRequestDTO messageRequestDTO) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setContent(messageRequestDTO.getContent());
        messageDTO.setSenderUsername(messageRequestDTO.getSenderUsername());
        messageDTO.setBookingId(bookingId);
        messageDTO.setCreationDate(LocalDateTime.now());
        messageDTO.setMessageStatus(MessageStatusEnum.SENT);
        return messageDTO;
    }

    private BookingResponseDTO getBookingDataById(Long bookingId) throws ExecutionException, InterruptedException {
        ProducerRecord<String, Long> producerRecord = new ProducerRecord<>(kafkaConfigProps.getBookingBookingDataByIdTopic, bookingId);
        producerRecord.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, kafkaConfigProps.getBookingBookingDataByIdReplyTopic.getBytes()));
        return bookingResponseDTOReplyingKafkaTemplate.sendAndReceive(producerRecord).get().value();
    }

    private void saveMessageInDatabase(MessageDTO messageDTO) {
        MessageEntity messageEntity = modelMapper.map(messageDTO, MessageEntity.class);
        messageRepository.save(messageEntity);
    }

}
