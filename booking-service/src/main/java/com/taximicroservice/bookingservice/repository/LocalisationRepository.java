package com.taximicroservice.bookingservice.repository;

import com.taximicroservice.bookingservice.model.entity.LocalisationEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LocalisationRepository extends PagingAndSortingRepository<LocalisationEntity, Long> {
}
