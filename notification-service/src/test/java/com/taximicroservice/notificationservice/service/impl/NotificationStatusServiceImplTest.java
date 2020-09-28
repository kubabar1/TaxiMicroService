package com.taximicroservice.notificationservice.service.impl;

import com.taximicroservice.notificationservice.exception.NotificationServiceException;
import com.taximicroservice.notificationservice.model.utils.NotificationStatusEnum;
import com.taximicroservice.notificationservice.service.NotificationService;
import com.taximicroservice.notificationservice.service.NotificationStatusService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NotificationStatusServiceImplTest {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private NotificationStatusService notificationStatusService;

    @Test
    void setNotificationAsRead() {
        assertEquals(NotificationStatusEnum.SENT, notificationService.getNotificationById(3L).getStatus());
        notificationStatusService.setNotificationAsRead(3L);
        assertEquals(NotificationStatusEnum.READ, notificationService.getNotificationById(3L).getStatus());
        assertThrows(NotificationServiceException.class, () -> notificationStatusService.setNotificationAsRead(7L));
        assertThrows(NotificationServiceException.class, () -> notificationStatusService.setNotificationAsRead(8L));
    }

    @Test
    void setNotificationAsDeleted() {
        assertEquals(NotificationStatusEnum.SENT, notificationService.getNotificationById(2L).getStatus());
        notificationStatusService.setNotificationAsDeleted(2L);
        notificationStatusService.setNotificationAsDeleted(6L);
        notificationStatusService.setNotificationAsDeleted(9L);
        assertEquals(NotificationStatusEnum.DELETED, notificationService.getNotificationById(2L).getStatus());
        assertEquals(NotificationStatusEnum.DELETED, notificationService.getNotificationById(6L).getStatus());
        assertEquals(NotificationStatusEnum.DELETED, notificationService.getNotificationById(9L).getStatus());
    }

}