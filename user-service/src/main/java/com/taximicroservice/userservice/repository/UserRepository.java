package com.taximicroservice.userservice.repository;

import com.taximicroservice.userservice.model.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
}
