package com.taximicroservice.bookingservice.repository;

import com.taximicroservice.bookingservice.model.entity.BookingEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookingRepository extends PagingAndSortingRepository<BookingEntity, Long> {
}
