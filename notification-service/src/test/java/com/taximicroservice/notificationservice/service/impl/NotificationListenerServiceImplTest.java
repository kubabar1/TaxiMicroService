package com.taximicroservice.notificationservice.service.impl;

import com.taximicroservice.notificationservice.model.dto.NotificationRequestDTO;
import com.taximicroservice.notificationservice.model.dto.NotificationResponseDTO;
import com.taximicroservice.notificationservice.model.utils.NotificationStatusEnum;
import com.taximicroservice.notificationservice.service.NotificationListenerService;
import com.taximicroservice.notificationservice.service.NotificationService;
import com.taximicroservice.notificationservice.utils.NotificationTestUtil;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NotificationListenerServiceImplTest {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private NotificationListenerService notificationListenerService;

    @Autowired
    private ModelMapper modelMapper;


    @Test
    void listenNotifications() {
        long notificationCountBefore = notificationService.getNotifications(0, 100).getTotalElements();
        NotificationRequestDTO notificationRequestDTO = NotificationTestUtil.createNotificationRequestDTO();
        notificationListenerService.listenNotifications(notificationRequestDTO);
        long sizeAfterAdd = notificationService.getNotifications(0, 100).getTotalElements();
        long expectedSizeAfterAdd = notificationCountBefore + 1;
        assertEquals(expectedSizeAfterAdd, sizeAfterAdd);
        NotificationResponseDTO notificationResponseDTO = modelMapper.map(notificationRequestDTO, NotificationResponseDTO.class);
        notificationResponseDTO.setStatus(NotificationStatusEnum.SENT);
        NotificationResponseDTO lastResponse = notificationService.getNotifications(0, 100).getContent().get((int) sizeAfterAdd - 1);
        assertEquals(notificationResponseDTO.getSender(), lastResponse.getSender());
        assertEquals(notificationResponseDTO.getReceiverUsername(), lastResponse.getReceiverUsername());
        assertEquals(notificationResponseDTO.getStatus(), lastResponse.getStatus());
        assertEquals(notificationResponseDTO.getType(), lastResponse.getType());
        assertEquals(notificationResponseDTO.getContent(), lastResponse.getContent());
    }

}