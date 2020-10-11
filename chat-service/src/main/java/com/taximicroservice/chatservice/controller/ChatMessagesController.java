package com.taximicroservice.chatservice.controller;

import com.taximicroservice.chatservice.exception.ChatServiceException;
import com.taximicroservice.chatservice.model.dto.MessageResponseDTO;
import com.taximicroservice.chatservice.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class ChatMessagesController {

    @Autowired
    private MessageService messageService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Page<MessageResponseDTO>> getMessages(@RequestParam(value = "page") int page,
                                                                @RequestParam(value = "count") int count) {
        Page<MessageResponseDTO> messageResponseDTOPage;

        try {
            messageResponseDTOPage = messageService.getMessages(page, count);
        } catch (ChatServiceException e) {
            return ResponseEntity.unprocessableEntity().build();
        }

        if (messageResponseDTOPage.getNumberOfElements() == 0) {
            return new ResponseEntity<>(messageResponseDTOPage, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(messageResponseDTOPage, HttpStatus.OK);
        }
    }

}
