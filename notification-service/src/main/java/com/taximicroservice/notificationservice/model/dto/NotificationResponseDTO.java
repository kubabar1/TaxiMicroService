package com.taximicroservice.notificationservice.model.dto;

import com.taximicroservice.notificationservice.model.utils.NotificationStatusEnum;
import com.taximicroservice.notificationservice.model.utils.NotificationTypeEnum;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class NotificationResponseDTO implements Serializable {

    private Long id;

    private NotificationTypeEnum type;

    private NotificationStatusEnum status;

    private String content;

    private String sender;

    private String receiverUsername;

    private LocalDateTime creationDate;

}
