package com.taximicroservice.notificationservice.model.dto;

import com.taximicroservice.notificationservice.model.utils.NotificationTypeEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class NotificationRequestDTO implements Serializable {

    private NotificationTypeEnum type;

    private String content;

    private String sender;

    private String receiverUsername;

}
// {"type":"INFO","content": "Test message","sender": "message-service","receiverUsername": "adam123"}