package com.taximicroservice.notificationservice.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;

}
