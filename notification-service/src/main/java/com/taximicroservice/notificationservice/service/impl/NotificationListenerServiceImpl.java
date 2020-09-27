package com.taximicroservice.notificationservice.service.impl;

import com.taximicroservice.notificationservice.model.dto.NotificationRequestDTO;
import com.taximicroservice.notificationservice.model.entity.NotificationEntity;
import com.taximicroservice.notificationservice.model.utils.NotificationStatusEnum;
import com.taximicroservice.notificationservice.repository.NotificationRepository;
import com.taximicroservice.notificationservice.service.NotificationListenerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationListenerServiceImpl implements NotificationListenerService {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    @KafkaListener(topics = "${notificationService.kafka.topics.sendUserNotificationTopic}", containerFactory = "notificationDTOListenerContainerFactory")
    public void listenNotifications(NotificationRequestDTO notificationRequestDTO) {
        simpMessagingTemplate.convertAndSendToUser(notificationRequestDTO.getReceiverUsername(), "/queue/reply", notificationRequestDTO);
        saveNotificationInDatabase(notificationRequestDTO);
    }

    private void saveNotificationInDatabase(NotificationRequestDTO notificationRequestDTO) {
        NotificationEntity notificationEntity = modelMapper.map(notificationRequestDTO, NotificationEntity.class);
        notificationEntity.setStatus(NotificationStatusEnum.SENT);
        notificationRepository.save(notificationEntity);
    }

}
