package com.taximicroservice.bookingservice.repository;

import com.taximicroservice.bookingservice.model.entity.BookingStatusEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookingStatusRepository extends PagingAndSortingRepository<BookingStatusEntity, Long> {
}
