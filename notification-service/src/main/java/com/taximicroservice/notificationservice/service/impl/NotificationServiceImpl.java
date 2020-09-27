package com.taximicroservice.notificationservice.service.impl;

import com.taximicroservice.notificationservice.exception.NotificationServiceException;
import com.taximicroservice.notificationservice.model.dto.NotificationResponseDTO;
import com.taximicroservice.notificationservice.repository.NotificationRepository;
import com.taximicroservice.notificationservice.service.NotificationService;
import com.taximicroservice.notificationservice.service.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<NotificationResponseDTO> getNotifications(int page, int count) throws NotificationServiceException {
        ValidationUtil.validatePageAndCount(page, count);
        return notificationRepository
                .findAll(PageRequest.of(page, count))
                .map(notificationEntity -> modelMapper.map(notificationEntity, NotificationResponseDTO.class));
    }

    @Override
    public Page<NotificationResponseDTO> getNotificationsByReceiverUsername(String receiverUsername, int page, int count) throws NotificationServiceException {
        ValidationUtil.validatePageAndCount(page, count);
        return notificationRepository
                .findByReceiverUsername(receiverUsername, PageRequest.of(page, count))
                .map(notificationEntity -> modelMapper.map(notificationEntity, NotificationResponseDTO.class));
    }

    @Override
    public NotificationResponseDTO getNotificationById(Long notificationId) throws EntityNotFoundException {
        return modelMapper.map(notificationRepository.findById(notificationId).orElseThrow(EntityNotFoundException::new),
                NotificationResponseDTO.class);
    }

}
