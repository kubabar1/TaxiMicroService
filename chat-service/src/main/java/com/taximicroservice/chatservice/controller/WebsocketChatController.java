package com.taximicroservice.chatservice.controller;

import com.taximicroservice.chatservice.model.dto.MessageDTO;
import com.taximicroservice.chatservice.model.dto.MessageRequestDTO;
import com.taximicroservice.chatservice.model.utils.MessageStatusEnum;
import com.taximicroservice.chatservice.repository.MessageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class WebsocketChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ModelMapper modelMapper;

    @MessageMapping("/topic/bookings/{bookingId}")
    public void listenMessages(@DestinationVariable String bookingId, @Payload MessageRequestDTO messageRequestDTO) {
        MessageDTO messageDTO = modelMapper.map(messageRequestDTO, MessageDTO.class);
        messageDTO.setBookingId(bookingId);
        messageDTO.setCreationDate(LocalDateTime.now());
        messageDTO.setMessageStatus(MessageStatusEnum.SENT);

        simpMessagingTemplate.convertAndSend("/queue/reply/bookings/" + bookingId, messageDTO);
        saveMessageInDatabase(messageDTO);
    }

    private void saveMessageInDatabase(MessageDTO messageDTO) {
        // NotificationEntity notificationEntity = modelMapper.map(notificationRequestDTO, NotificationEntity.class);
        // notificationEntity.setStatus(NotificationStatusEnum.SENT);
        // notificationEntity.setCreationDate(LocalDateTime.now());
        // notificationRepository.save(notificationEntity);
    }

}
