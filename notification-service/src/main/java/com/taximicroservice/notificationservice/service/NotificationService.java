package com.taximicroservice.notificationservice.service;

import com.taximicroservice.notificationservice.exception.NotificationServiceException;
import com.taximicroservice.notificationservice.model.dto.NotificationResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;

public interface NotificationService {

    Page<NotificationResponseDTO> getNotifications(int page, int count) throws NotificationServiceException;

    Page<NotificationResponseDTO> getNotificationsByReceiverUsername(String receiverUsername, int page, int count) throws NotificationServiceException;

    NotificationResponseDTO getNotificationById(Long notificationId) throws EntityNotFoundException;

}
