package com.taximicroservice.chatservice.service;

import com.taximicroservice.chatservice.model.dto.MessageRequestDTO;

public interface WebsocketChatService {

    void sendMessageToUsers(Long bookingId, MessageRequestDTO messageRequestDTO);

}
