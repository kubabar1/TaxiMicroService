package com.taximicroservice.notificationservice.repository;

import com.taximicroservice.notificationservice.model.entity.NotificationEntity;
import com.taximicroservice.notificationservice.utils.NotificationTestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NotificationRepositoryTest {

    @Autowired
    private NotificationRepository notificationRepository;

    @Test
    void findByReceiverUsername() {
        Page<NotificationEntity> notificationEntityPage =  notificationRepository.findByReceiverUsername("adam123", PageRequest.of(0, 100));
        assertEquals(11, notificationEntityPage.getNumberOfElements());
        assertEquals(11, notificationEntityPage.getTotalElements());
        assertEquals(1, notificationEntityPage.getTotalPages());
        assertEquals(100, notificationEntityPage.getSize());
        assertEquals(0, notificationEntityPage.getNumber());
        assertEquals(NotificationTestUtil.getNotificationEntity1(), notificationEntityPage.getContent().get(0));
    }

}