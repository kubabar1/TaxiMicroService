package com.taximicroservice.chatservice.model.entity;

import com.taximicroservice.chatservice.model.utils.MessageStatusEnum;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name ="messages")
public class MessageEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sender_username", nullable = false)
    private String senderUsername;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private MessageStatusEnum status;

    @Column(name = "booking_id", nullable = false)
    private String bookingId;

}
