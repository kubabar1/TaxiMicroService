package com.taximicroservice.bookingservice.repository;

import com.taximicroservice.bookingservice.model.entity.BookingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookingRepository extends PagingAndSortingRepository<BookingEntity, Long>, JpaSpecificationExecutor<BookingEntity> {

    Page<BookingEntity> findByDriverIdAndStatus_id(Long driverId, String bookingId, Pageable pageable);

    Page<BookingEntity> findByDriverId(Long driverId, Pageable pageable);

    Page<BookingEntity> findByPassengerId(Long passengerId, Pageable pageable);

}
