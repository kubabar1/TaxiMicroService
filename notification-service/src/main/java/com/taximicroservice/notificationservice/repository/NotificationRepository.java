package com.taximicroservice.notificationservice.repository;

import com.taximicroservice.notificationservice.model.entity.NotificationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NotificationRepository extends PagingAndSortingRepository<NotificationEntity, Long> {

    Page<NotificationEntity> findByReceiverUsername(String receiverUsername, Pageable pageable);

    Page<NotificationEntity> findByStatus(String statusId, PageRequest of);

}
