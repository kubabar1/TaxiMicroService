package com.taximicroservice.notificationservice.utils;

import com.taximicroservice.notificationservice.model.dto.NotificationRequestDTO;
import com.taximicroservice.notificationservice.model.dto.NotificationResponseDTO;
import com.taximicroservice.notificationservice.model.entity.NotificationEntity;
import com.taximicroservice.notificationservice.model.utils.NotificationStatusEnum;
import com.taximicroservice.notificationservice.model.utils.NotificationTypeEnum;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NotificationTestUtil {

    protected final static DateTimeFormatter DATE_TIME_FORMATTER;

    static {
        DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    public static NotificationResponseDTO getNotificationResponseDTO1() {
        NotificationResponseDTO notificationResponseDTO = new NotificationResponseDTO();
        notificationResponseDTO.setId(1L);
        notificationResponseDTO.setContent("Driver Bruce Wayne assigned to your booking");
        notificationResponseDTO.setCreationDate(LocalDateTime.parse("2020-09-14 09:01:44", DATE_TIME_FORMATTER));
        notificationResponseDTO.setType(NotificationTypeEnum.INFO);
        notificationResponseDTO.setStatus(NotificationStatusEnum.SENT);
        notificationResponseDTO.setReceiverUsername("adam123");
        notificationResponseDTO.setSender("booking-service");
        return notificationResponseDTO;
    }

    public static NotificationEntity getNotificationEntity1() {
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setId(1L);
        notificationEntity.setContent("Driver Bruce Wayne assigned to your booking");
        notificationEntity.setCreationDate(LocalDateTime.parse("2020-09-14 09:01:44", DATE_TIME_FORMATTER));
        notificationEntity.setType(NotificationTypeEnum.INFO);
        notificationEntity.setStatus(NotificationStatusEnum.SENT);
        notificationEntity.setReceiverUsername("adam123");
        notificationEntity.setSender("booking-service");
        return notificationEntity;
    }

    public static NotificationRequestDTO createNotificationRequestDTO() {
        NotificationRequestDTO notificationRequestDTO = new NotificationRequestDTO();
        notificationRequestDTO.setType(NotificationTypeEnum.INFO);
        notificationRequestDTO.setSender("payment-service");
        notificationRequestDTO.setContent("Payment was accepted");
        notificationRequestDTO.setReceiverUsername("adam1234");
        return notificationRequestDTO;
    }

}
