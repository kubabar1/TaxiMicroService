package com.taximicroservice.notificationservice.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.taximicroservice.notificationservice.model.utils.NotificationTypeEnum;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class NotificationRequestDTO implements Serializable {

    private NotificationTypeEnum type;

    private String content;

    private String sender;

    private String receiverUsername;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
    private LocalDateTime creationDate;

}
// {"type":"INFO","content": "Test message","sender": "message-service","receiverUsername": "adam123", "creationDate": "2020-09-14 09:01:44.000000"}