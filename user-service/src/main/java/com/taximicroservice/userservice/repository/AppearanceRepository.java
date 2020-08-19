package com.taximicroservice.userservice.repository;

import com.taximicroservice.userservice.model.entity.AppearanceEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AppearanceRepository extends PagingAndSortingRepository<AppearanceEntity, String> {
}
