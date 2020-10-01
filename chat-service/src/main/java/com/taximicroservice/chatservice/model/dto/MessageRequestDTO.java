package com.taximicroservice.chatservice.model.dto;

import com.taximicroservice.chatservice.model.utils.MessageStatusEnum;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class MessageRequestDTO implements Serializable {

    private String senderId;

    private String content;

    private LocalDateTime creationDate;

    private MessageStatusEnum messageStatus;

    private String bookingId;

}
