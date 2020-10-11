package com.taximicroservice.chatservice.service;

import com.taximicroservice.chatservice.model.dto.MessageResponseDTO;
import org.springframework.data.domain.Page;

public interface MessageService {

    Page<MessageResponseDTO> getMessages(int page, int count);

}
