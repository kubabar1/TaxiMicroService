package com.taximicroservice.userservice.repository;

import com.taximicroservice.userservice.model.entity.LanguageEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LanguageRepository extends PagingAndSortingRepository<LanguageEntity, String> {
}
