package com.taximicroservice.chatservice.repository;

import com.taximicroservice.chatservice.model.entity.MessageEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MessageRepository extends PagingAndSortingRepository<MessageEntity, Long> {
}
