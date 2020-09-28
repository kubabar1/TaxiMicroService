package com.taximicroservice.notificationservice.service;

import com.taximicroservice.notificationservice.exception.NotificationServiceException;
import com.taximicroservice.notificationservice.model.dto.NotificationResponseDTO;
import org.springframework.data.domain.Page;

import javax.persistence.EntityNotFoundException;

public interface NotificationStatusService {

    NotificationResponseDTO setNotificationAsRead(Long notificationId) throws EntityNotFoundException, NotificationServiceException;

    NotificationResponseDTO setNotificationAsDeleted(Long notificationId) throws EntityNotFoundException;

}
