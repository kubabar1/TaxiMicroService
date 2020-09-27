package com.taximicroservice.notificationservice.service;

import com.taximicroservice.notificationservice.model.dto.NotificationRequestDTO;

public interface NotificationListenerService {

    void listenNotifications(NotificationRequestDTO notificationRequestDTO);

}
