package com.taximicroservice.chatservice.controller;

import com.taximicroservice.chatservice.model.dto.MessageRequestDTO;
import com.taximicroservice.chatservice.service.WebsocketChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
public class WebsocketChatController {

    @Autowired
    private WebsocketChatService websocketChatService;

    @MessageMapping("/topic/bookings/{bookingId}")
    public void listenMessages(@DestinationVariable Long bookingId, @Payload MessageRequestDTO messageRequestDTO) {
        websocketChatService.sendMessageToUsers(bookingId, messageRequestDTO);
    }

}
