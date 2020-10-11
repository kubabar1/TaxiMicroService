package com.taximicroservice.chatservice.model.dto;

import com.taximicroservice.chatservice.model.utils.MessageStatusEnum;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class MessageDTO implements Serializable {

    private String senderUsername;

    private String content;

    private LocalDateTime creationDate;

    private MessageStatusEnum messageStatus;

    private Long bookingId;

}
