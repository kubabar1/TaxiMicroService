package com.taximicroservice.bookingservice.repository;

import com.taximicroservice.bookingservice.model.entity.BookingEntity;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface BookingRepository extends PagingAndSortingRepository<BookingEntity, Long>, JpaSpecificationExecutor<BookingEntity> {

    Page<BookingEntity> findByDriverIdAndStatus_id(Long driverId, String bookingId, Pageable pageable);

    Page<BookingEntity> findByDriverId(Long driverId, Pageable pageable);

    Page<BookingEntity> findByPassengerId(Long passengerId, Pageable pageable);

    @Query("SELECT b FROM BookingEntity b WHERE ST_DWithin(:driverLocalisation, :driverLocalisation, :distance) = true")
    Page<BookingEntity> getNearbyCreatedBookings(@Param("driverLocalisation") Geometry driverLocalisation, @Param("distance") double distance, Pageable pageable);

}
