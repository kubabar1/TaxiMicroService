package com.taximicroservice.notificationservice.service.impl;

import com.taximicroservice.notificationservice.exception.NotificationServiceException;
import com.taximicroservice.notificationservice.model.dto.NotificationResponseDTO;
import com.taximicroservice.notificationservice.service.NotificationService;
import com.taximicroservice.notificationservice.utils.NotificationTestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NotificationServiceImplTest {

    @Autowired
    private NotificationService notificationService;


    @Test
    void getNotifications() {
        Page<NotificationResponseDTO> notificationResponseDTOPage = notificationService.getNotifications(0, 5);
        assertEquals(5, notificationResponseDTOPage.getNumberOfElements());
        assertEquals(3, notificationResponseDTOPage.getTotalPages());
        assertEquals(5, notificationResponseDTOPage.getSize());
        assertEquals(0, notificationResponseDTOPage.getNumber());
        assertEquals(NotificationTestUtil.getNotificationResponseDTO1(), notificationResponseDTOPage.getContent().get(0));

        assertThrows(NotificationServiceException.class, () -> notificationService.getNotifications(0, 0));
        assertThrows(NotificationServiceException.class, () -> notificationService.getNotifications(-1, 0));
        assertThrows(NotificationServiceException.class, () -> notificationService.getNotifications(-1, 10));
    }

    @Test
    void getNotificationsByReceiverUsername() {
        Page<NotificationResponseDTO> notificationResponseDTOPage = notificationService.getNotificationsByReceiverUsername("adam123", 0, 100);
        assertEquals(11, notificationResponseDTOPage.getNumberOfElements());
        assertEquals(11, notificationResponseDTOPage.getTotalElements());
        assertEquals(1, notificationResponseDTOPage.getTotalPages());
        assertEquals(100, notificationResponseDTOPage.getSize());
        assertEquals(0, notificationResponseDTOPage.getNumber());
        assertEquals(NotificationTestUtil.getNotificationResponseDTO1(), notificationResponseDTOPage.getContent().get(0));

        assertThrows(NotificationServiceException.class, () -> notificationService.getNotificationsByReceiverUsername("adam123", 0, 0));
        assertThrows(NotificationServiceException.class, () -> notificationService.getNotificationsByReceiverUsername("adam123", -1, 0));
        assertThrows(NotificationServiceException.class, () -> notificationService.getNotificationsByReceiverUsername("adam123", -1, 10));
    }

    @Test
    void getNotificationById() {
        NotificationResponseDTO notificationResponseDTO = notificationService.getNotificationById(1L);
        assertEquals(NotificationTestUtil.getNotificationResponseDTO1(), notificationResponseDTO);
    }

}