package com.taximicroservice.chatservice.service.impl;

import com.taximicroservice.chatservice.model.dto.MessageResponseDTO;
import com.taximicroservice.chatservice.repository.MessageRepository;
import com.taximicroservice.chatservice.service.MessageService;
import com.taximicroservice.chatservice.service.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ValidationUtil validationUtil;


    @Override
    public Page<MessageResponseDTO> getMessages(int page, int count) {
        validationUtil.validatePageAndCount(page, count);
        return messageRepository
                .findAll(PageRequest.of(page, count))
                .map(messageEntity -> modelMapper.map(messageEntity, MessageResponseDTO.class));
    }

}
