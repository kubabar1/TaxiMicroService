package com.taximicroservice.chatservice.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MessageRequestDTO implements Serializable {

    private String senderUsername;

    private String content;

}
