package com.taximicroservice.notificationservice.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class NotificationDTO implements Serializable {

    private String type;

    private String content;

    private String senderUsername;

    private String receiverUsername;

}
