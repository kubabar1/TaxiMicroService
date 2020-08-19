package com.taximicroservice.userservice.repository;

import com.taximicroservice.userservice.model.entity.UserSettingsEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserSettingsRepository extends PagingAndSortingRepository<UserSettingsEntity, String> {
}
