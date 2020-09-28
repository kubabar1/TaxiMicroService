package com.taximicroservice.notificationservice.service.impl;

import com.taximicroservice.notificationservice.exception.NotificationServiceException;
import com.taximicroservice.notificationservice.model.dto.NotificationResponseDTO;
import com.taximicroservice.notificationservice.model.entity.NotificationEntity;
import com.taximicroservice.notificationservice.model.utils.NotificationStatusEnum;
import com.taximicroservice.notificationservice.repository.NotificationRepository;
import com.taximicroservice.notificationservice.service.NotificationStatusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class NotificationStatusServiceImpl implements NotificationStatusService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public NotificationResponseDTO setNotificationAsRead(Long notificationId) throws EntityNotFoundException, NotificationServiceException {
        NotificationEntity notificationEntity = notificationRepository.findById(notificationId).orElseThrow(EntityNotFoundException::new);
        if (notificationEntity.getStatus().equals(NotificationStatusEnum.SENT)) {
            notificationEntity.setStatus(NotificationStatusEnum.READ);
            return modelMapper.map(notificationRepository.save(notificationEntity), NotificationResponseDTO.class);
        } else {
            throw new NotificationServiceException("Incorrect notification current status");
        }
    }

    @Override
    public NotificationResponseDTO setNotificationAsDeleted(Long notificationId) throws EntityNotFoundException {
        NotificationEntity notificationEntity = notificationRepository.findById(notificationId).orElseThrow(EntityNotFoundException::new);
        notificationEntity.setStatus(NotificationStatusEnum.DELETED);
        return modelMapper.map(notificationRepository.save(notificationEntity), NotificationResponseDTO.class);
    }

}
